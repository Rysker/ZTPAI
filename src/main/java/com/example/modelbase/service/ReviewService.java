package com.example.modelbase.service;

import com.example.modelbase.dto.request.ReviewRequestDto;
import com.example.modelbase.model.Like;
import com.example.modelbase.model.ModelKit;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.ReviewStatus;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.LikeRepository;
import com.example.modelbase.repository.ModelKitRepository;
import com.example.modelbase.repository.ReviewRepository;
import com.example.modelbase.repository.ReviewStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService
{
    private final ReviewRepository reviewRepository;
    private final ReviewStatusRepository reviewStatusRepository;
    private final LikeRepository likeRepository;
    private final ModelKitRepository modelKitRepository;
    private final UserService userService;

    public void addLike(String token, Integer reviewId, Boolean isLiked) throws Exception
    {
        Like like = new Like();
        User currentUser = userService.getUserFromToken(token);
        Optional<Review> review = reviewRepository.getReviewById(reviewId);
        if(review.isPresent())
        {
            Optional<Like> existingLike = likeRepository.getLikeByUserAndReview(currentUser, review.get());
            if(existingLike.isEmpty())
            {
                like.setIsLike(isLiked);
                like.setUser(currentUser);
                like.setReview(review.get());
                likeRepository.save(like);
            }
        }
        else
            throw new IllegalArgumentException("No review found!");
    }

    public void swapLike(String token, Integer reviewId) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<Review> review = reviewRepository.getReviewById(reviewId);
        if(review.isPresent())
        {
            Optional<Like> like = likeRepository.getLikeByUserAndReview(currentUser, review.get());
            if(like.isPresent())
            {
                Like tmp = like.get();
                tmp.setIsLike(!tmp.getIsLike());
                likeRepository.save(tmp);
            }
            else
                throw new NoSuchElementException("No like found");
        }
        else
            throw new IllegalArgumentException("No review found!");
    }

    public void deleteLike(String token, Integer reviewId) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<Review> review = reviewRepository.getReviewById(reviewId);
        if(review.isPresent())
        {
            Optional<Like> like = likeRepository.getLikeByUserAndReview(currentUser, review.get());
            if(like.isPresent())
                likeRepository.delete(like.get());
            else
                throw new NoSuchElementException("No like found");
        }
        else
            throw new IllegalArgumentException("No review found!");
    }

    public Integer addReview(String token, Integer kitId, ReviewRequestDto request) throws Exception
    {
        if(request.getTitle().isBlank() || request.getDescription().isBlank())
            throw new IllegalArgumentException("Cannot create review with empty fields!");

        User currentUser = userService.getUserFromToken(token);
        Optional<ModelKit> kit = modelKitRepository.findById(kitId);
        if(kit.isPresent())
        {
            Optional<Review> existingReview = reviewRepository.findByUserAndModelKit(currentUser, kit.get());
            if (existingReview.isPresent())
                throw new IllegalArgumentException("Review already exists!");

            BigDecimal score = request.getScore().multiply(BigDecimal.valueOf(10));
            Review review = new Review();
            ReviewStatus status = reviewStatusRepository.getReviewStatusByName("OK");
            review.setReviewStatus(status);
            review.setContent(request.getDescription());
            review.setUser(currentUser);
            review.setRating(score.intValue());
            review.setTitle(request.getTitle());
            review.setWriteDate(LocalDate.now());
            review.setModelKit(kit.get());
            Review savedReview = reviewRepository.save(review);
            return savedReview.getId();
        }
        else
            throw new IllegalArgumentException("Model kit does not exist!");
    }

    public void deleteReview(String token, Integer reviewId) throws Exception
    {
        User currentUser = userService.getUserFromToken(token);
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isPresent() && currentUser.getReviews().contains(review.get()))
            reviewRepository.delete(review.get());
        else
            throw new IllegalArgumentException("Review cannot be deleted!");
    }
}
