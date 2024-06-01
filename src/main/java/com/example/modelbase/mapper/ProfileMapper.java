package com.example.modelbase.mapper;

import com.example.modelbase.dto.response.CollectionStatisticsDto;
import com.example.modelbase.dto.response.ProfileCollectionDto;
import com.example.modelbase.dto.response.ProfileResponseDto;
import com.example.modelbase.dto.response.SmallCollectibleDto;
import com.example.modelbase.model.Collectible;
import com.example.modelbase.model.Collection;
import com.example.modelbase.model.Like;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.FollowerListRepository;
import com.example.modelbase.repository.ReviewRepository;
import com.example.modelbase.repository.UserRepository;
import com.example.modelbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileMapper
{
    private final UserService userService;
    private final UserRepository userRepository;
    private final FollowerListRepository followerListRepository;
    private final ReviewRepository reviewRepository;

    public ProfileResponseDto mapProfile(String token, String username) throws Exception
    {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        User user = userService.getUserFromToken(token);

        if(optionalUser.isPresent())
        {
            User profileUser = optionalUser.get();
            ProfileResponseDto responseDto = new ProfileResponseDto();

            //Simple mapping
            responseDto.setDescription(profileUser.getDescription());
            responseDto.setUsername(profileUser.getUsername());
            responseDto.setAvatar(profileUser.getAvatar());
            responseDto.setIsFollowed(followerListRepository.existsByFollowerAndFollowed(user, profileUser).toString());
            responseDto.setMemberSince(profileUser.dateToString());
            //Require additional functions
            responseDto.setIsSameUser(isSameUser(user, profileUser));

            //Additonal mapping
            mapReviews(responseDto, profileUser);
            mapCollection(responseDto, profileUser);
            responseDto.setStats(getStatistics(List.copyOf(profileUser.getCollection().getCollectibles())));
            return responseDto;
        }
        else
            throw new IllegalArgumentException("User does not exist!");
    }

    private String isSameUser(User user, User profileUser)
    {
        if(user == profileUser)
            return "true";
        else
            return "false";
    }

    private void mapReviews(ProfileResponseDto responseDto, User profileUser)
    {
        List<Review> reviews = reviewRepository.getReviewsByUser(profileUser);
        int reputation = 0;

        for(Review review: reviews)
        {
            for(Like like: review.getLikes())
            {
                if(like.getIsLike())
                    reputation++;
                else
                    reputation--;
            }
        }

        responseDto.setReputation(reputation);
        responseDto.setReviewsCount(reviews.size());
    }

    private void mapCollection(ProfileResponseDto responseDto, User profileUser)
    {
        ProfileCollectionDto collectionDto = new ProfileCollectionDto();
        Collection collection = profileUser.getCollection();
        List<Collectible> list = new ArrayList<>(collection.getCollectibles());
        List<Collectible> topList = new ArrayList<>();
        List<SmallCollectibleDto> collectibleDtos = new ArrayList<>();

        list.sort(Comparator.comparing(Collectible::getListOrder));

        int topItemsCount = 3;
        for (int i = 0; i < Math.min(topItemsCount, list.size()); i++)
        {
            Collectible item = list.get(i);
            if(item.getIsPublic())
                topList.add(list.get(i));
        }

        for(Collectible collectible: topList)
        {
            SmallCollectibleDto collectibleDto = this.mapToSmallCollectibleDto(collectible);
            collectibleDtos.add(collectibleDto);
        }

        collectionDto.setCollectibles(collectibleDtos);
        collectionDto.setId(collection.getId());
        collectionDto.setCount(list.size());
        responseDto.setProfileCollection(collectionDto);
    }

    private SmallCollectibleDto mapToSmallCollectibleDto(Collectible collectible)
    {
        SmallCollectibleDto smallCollectibleDto = new SmallCollectibleDto();
        smallCollectibleDto.setText(collectible.getModelKit().getVariant().getVehicle().getName());
        smallCollectibleDto.setOrder(collectible.getListOrder());

        //Retrieve mainPhoto from ModelKit
        String photo = collectible.getModelKit().getMainPhoto();
        if(photo.equals("Error!"))
            throw new IllegalArgumentException("Model does not have main photo!");
        smallCollectibleDto.setBackgroundImage(photo);

        return smallCollectibleDto;
    }

    private List<CollectionStatisticsDto> getStatistics(List<Collectible> collectibles)
    {
        Map<String, Integer> values = new TreeMap<>();

        for(Collectible collectible: collectibles)
            values.merge(collectible.getModelKit().getUseCountry().getName(), 1, Integer::sum);

        List<CollectionStatisticsDto> statistics = values.entrySet().stream()
                .map(entry -> new CollectionStatisticsDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return statistics;
    }
}
