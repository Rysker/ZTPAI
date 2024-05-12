package com.example.modelbase.service;

import com.example.modelbase.model.Like;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.LikeRepository;
import com.example.modelbase.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService
{
    private final ReviewRepository reviewRepository;
    private final LikeRepository likeRepository;
    private final UserService userService;

    public void addLike(String token, Integer reviewId, Boolean isLiked) throws Exception
    {
        Like like = new Like();
        User currentUser = userService.getUserFromToken(token);
        Optional<Review> review = reviewRepository.getReviewById(reviewId);
        if(review.isPresent())
        {
            like.setIsLike(isLiked);
            like.setUser(currentUser);
            like.setReview(review.get());
            likeRepository.save(like);
        }
        else
            throw new IllegalArgumentException("Element is already liked!");
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
            throw new RuntimeException("Unknown error!");
    }
}
