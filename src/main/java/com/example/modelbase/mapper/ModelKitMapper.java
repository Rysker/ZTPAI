package com.example.modelbase.mapper;

import com.example.modelbase.dto.response.ModelKitShortDto;
import com.example.modelbase.model.EavTable;
import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.CollectibleRepository;
import com.example.modelbase.repository.EavTableRepository;
import com.example.modelbase.repository.WishlistRepository;
import com.example.modelbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ModelKitMapper
{
    private final EavTableRepository eavTableRepository;
    private final WishlistRepository wishlistRepository;
    private final CollectibleRepository collectibleRepository;
    private final UserService userService;
    public ModelKitShortDto kitShortMap(String token, ModelKit modelKit)
    {
        String scale = eavTableRepository.findByModelKitIdAndAttributeName(modelKit.getId(), "Scale")
                .map(EavTable::getValue)
                .orElse("");

        User current_user = userService.getUserFromToken(token);
        return ModelKitShortDto.builder()
                .photo(modelKit.getPhotos().stream().findFirst().get().getImage())
                .name(modelKit.getName())
                .manufacturerCode(modelKit.getManufacturerCode())
                .scale(scale)
                .status(collectibleRepository.findByIdAndModelKit(current_user.getId(), modelKit).getProgress().getName())
                .manufacturer(modelKit.getManufacturer().getName())
                .variant(modelKit.getVariant().getName())
                .reviewsCount(String.valueOf(modelKit.getReviews().size()))
                .reviewsAverage(calculateReviewsAverage(modelKit.getReviews()))
                .isOnWatchlist(isModelKitInUserWatchlist(current_user, modelKit))
                .build();
    }

    private String calculateReviewsAverage(Set<Review> reviews)
    {
        Double res = 0.0;
        for(Review review: reviews)
            res += review.getRating();
        return Double.toString((res/reviews.size() / 10.0));
    }

    private boolean isModelKitInUserWatchlist(User user, ModelKit modelKit)
    {
        return wishlistRepository.existsByUserAndModelKit(user, modelKit);
    }
}
