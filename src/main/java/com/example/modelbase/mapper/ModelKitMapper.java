package com.example.modelbase.mapper;

import com.example.modelbase.dto.response.ModelKitDto;
import com.example.modelbase.model.*;
import com.example.modelbase.model.Collection;
import com.example.modelbase.repository.CollectibleRepository;
import com.example.modelbase.repository.EavTableRepository;
import com.example.modelbase.repository.UserRepository;
import com.example.modelbase.repository.WishlistRepository;
import com.example.modelbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ModelKitMapper
{
    private final EavTableRepository eavTableRepository;
    private final WishlistRepository wishlistRepository;
    private final CollectibleRepository collectibleRepository;
    private final UserService userService;
    private final ReviewMapper reviewMapper;
    private final UserRepository userRepository;

    public ModelKitDto kitShortMap(String token, ModelKit modelKit)
    {
        String scale = eavTableRepository.findByModelKitIdAndAttributeName(modelKit.getId(), "Scale")
                .map(EavTable::getValue)
                .orElse("");

        User current_user = userService.getUserFromToken(token);

        return getModelKitDto(modelKit, scale, current_user);
    }

    private ModelKitDto getModelKitDto(ModelKit modelKit, String scale, User current_user)
    {
        String status = this.findStatus(current_user, modelKit);

        return ModelKitDto.builder()
                .id(modelKit.getId())
                .photo(modelKit.getPhotos().stream().filter(photo -> photo.getIs_main() == Boolean.TRUE).findFirst().get().getImage())
                .name(modelKit.getName())
                .manufacturerCode(modelKit.getManufacturerCode())
                .scale(scale)
                .manufacturer(modelKit.getManufacturer().getName())
                .status(status)
                .variant(modelKit.getVariant().getName())
                .reviewsCount(String.valueOf(modelKit.getReviews().size()))
                .reviewsAverage(calculateReviewsAverage(modelKit.getReviews()))
                .isOnWatchlist(isModelKitInUserWatchlist(current_user, modelKit))
                .build();
    }

    public ModelKitDto kitLongMap(String token, ModelKit modelKit)
    {
        ModelKitDto modelKitDto = this.kitShortMap(token, modelKit);
        Set<String> photos = new LinkedHashSet();
        mapPhotos(photos, modelKit.getPhotos());
        modelKitDto.setPhotos(photos);
        modelKitDto.setReviews(reviewMapper.mapReviews(token, modelKit.getReviews()));
        return modelKitDto;
    }

    public ModelKitDto userShortMap(String username, ModelKit modelKit) throws Exception
    {
        String scale = eavTableRepository.findByModelKitIdAndAttributeName(modelKit.getId(), "Scale")
                .map(EavTable::getValue)
                .orElse("");

        Optional<User> tmp = userRepository.findUserByUsername(username);
        if(tmp.isPresent())
        {
            User current_user = tmp.get();
            return getModelKitDto(modelKit, scale, current_user);
        }
        else
            throw new IllegalArgumentException("User not found!");
    }

    public ModelKitDto userCollectionMap(String username, ModelKit modelKit) throws Exception
    {
        Optional<User> tmp = userRepository.findUserByUsername(username);
        if(tmp.isPresent())
        {
            User user = tmp.get();
            ModelKitDto modelKitDto = this.userShortMap(username, modelKit);
            Set<String> photos = new LinkedHashSet<>();
            mapPhotos(photos, modelKit.getPhotos());
            modelKitDto.setPhotos(photos);
            modelKitDto.setReviews(reviewMapper.mapUsernameReviews(username, modelKit.getReviews()));
            return getModelKitDto(modelKit, user, modelKitDto);
        }
        else
            throw new IllegalArgumentException("User not found!");
    }

    private ModelKitDto getModelKitDto(ModelKit modelKit, User user, ModelKitDto modelKitDto)
    {
        Optional<Collectible> collectible = collectibleRepository.findByCollectionAndModelKit(user.getCollection(), modelKit);
        Collectible collectible1 = collectible.get();
        modelKitDto.setCollectibleId(collectible1.getId());
        modelKitDto.setCompletionDate(collectible1.getCompletionDate());
        modelKitDto.setIsPublic(collectible1.getIsPublic().toString());
        return modelKitDto;
    }

    private Double calculateReviewsAverage(Set<Review> reviews)
    {
        Double res = 0.0;
        for(Review review: reviews)
            res += review.getRating();
        return (res / (reviews.size() * 10));
    }

    private boolean isModelKitInUserWatchlist(User user, ModelKit modelKit)
    {
        return wishlistRepository.existsByUserAndModelKit(user, modelKit);
    }

    private void mapPhotos(Set<String> photos, Set<Photo> setPhotos)
    {
        List<Photo> photoList = setPhotos.stream().sorted((p1, p2) -> Boolean.compare(p2.getIs_main(), p1.getIs_main())).toList();
        for(Photo photo: photoList)
            photos.add(photo.getImage());
    }

    private String findStatus(User current_user, ModelKit modelKit)
    {
        Collection userCollection = current_user.getCollection();
        Optional<Collectible> kit = collectibleRepository.findByCollectionAndModelKit(userCollection, modelKit);
        if(kit.isPresent())
            return kit.get().getProgress().getName();
        return "Nonowned";
    }
}
