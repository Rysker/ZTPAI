package com.example.modelbase.mapper;

import com.example.modelbase.dto.response.ModelKitDto;
import com.example.modelbase.dto.response.ReviewResponseDto;
import com.example.modelbase.model.*;
import com.example.modelbase.repository.CollectibleRepository;
import com.example.modelbase.repository.EavTableRepository;
import com.example.modelbase.repository.WishlistRepository;
import com.example.modelbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ModelKitMapper
{
    private final EavTableRepository eavTableRepository;
    private final WishlistRepository wishlistRepository;
    private final CollectibleRepository collectibleRepository;
    private final UserService userService;
    private final ReviewMapper reviewMapper;
    public ModelKitDto kitShortMap(String token, ModelKit modelKit)
    {
        String scale = eavTableRepository.findByModelKitIdAndAttributeName(modelKit.getId(), "Scale")
                .map(EavTable::getValue)
                .orElse("");

        User current_user = userService.getUserFromToken(token);

        String status = this.findStatus(current_user, modelKit);

        return ModelKitDto.builder()
                .id(modelKit.getId())
                .photo(modelKit.getPhotos().stream().findFirst().get().getImage())
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
        Set<String> photos = new HashSet<>();
        mapPhotos(photos, modelKit.getPhotos());
        modelKitDto.setPhotos(photos);
        modelKitDto.setReviews(reviewMapper.mapReviews(token, modelKit.getReviews()));
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
        for(Photo photo: setPhotos)
            photos.add(photo.getImage());
    }

    private String findStatus(User current_user, ModelKit modelKit)
    {
        Collection userCollection = current_user.getCollection();
        Optional<Collectible> kit = collectibleRepository.findByCollectionAndModelKit(userCollection, modelKit);
        if(kit.isPresent())
            return kit.get().getProgress().getName();
        return "NONOWNED";
    }

}
