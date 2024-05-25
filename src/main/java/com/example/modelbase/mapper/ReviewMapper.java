package com.example.modelbase.mapper;

import com.example.modelbase.dto.response.ReviewResponseDto;
import com.example.modelbase.model.Like;
import com.example.modelbase.model.Review;
import com.example.modelbase.model.User;
import com.example.modelbase.repository.UserRepository;
import com.example.modelbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReviewMapper
{
    private final UserService userService;
    private final UserRepository userRepository;

    public Set<ReviewResponseDto> mapReviews(String token, Set<Review> setReviews)
    {
        Set<ReviewResponseDto> reviews = new HashSet<>();
        User user = userService.getUserFromToken(token);
        return getReviewResponseDtos(setReviews, reviews, user);
    }

    private Set<ReviewResponseDto> getReviewResponseDtos(Set<Review> setReviews, Set<ReviewResponseDto> reviews, User user)
    {
        for(Review review: setReviews)
        {
            ReviewResponseDto responseDto = new ReviewResponseDto();
            BeanUtils.copyProperties(review, responseDto);
            responseDto.setRating(responseDto.getRating()/ 10);
            distinguishLikes(user, responseDto, review.getLikes());
            responseDto.setReviewId(review.getId());
            responseDto.setUsername(review.getUser().getUsername());
            reviews.add(responseDto);
        }
        return reviews;
    }

    public Set<ReviewResponseDto> mapUsernameReviews(String username, Set<Review> setReviews) throws Exception
    {
        Set<ReviewResponseDto> reviews = new HashSet<>();
        Optional<User> tmp = userRepository.findUserByUsername(username);
        if(tmp.isPresent())
        {
            User user = tmp.get();
            return getReviewResponseDtos(setReviews, reviews, user);
        }
        else
            throw new IllegalArgumentException("No user found!");
    }

    private void distinguishLikes(User user, ReviewResponseDto reviewDto, Set<Like> likes)
    {
        int downvotes = 0;
        String userLike = "NONE";
        for(Like like: likes)
        {
            if(!like.getIsLike())
                downvotes++;
            if(like.getUser().getId().compareTo(user.getId()) == 0)
                userLike = like.getIsLike().toString();
        }
        reviewDto.setScore((likes.size() - 2 * downvotes));
        reviewDto.setUserLike(userLike);
    }
}
