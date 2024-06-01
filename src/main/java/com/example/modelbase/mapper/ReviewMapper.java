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

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewMapper
{
    private final UserService userService;
    private final UserRepository userRepository;

    public List<ReviewResponseDto> mapReviews(String token, Set<Review> setReviews)
    {
        User user = userService.getUserFromToken(token);
        return getReviewResponseDtos(setReviews, user);
    }

    private List<ReviewResponseDto> getReviewResponseDtos(Set<Review> setReviews, User user)
    {
        List<Review> listReviews = new ArrayList<>(setReviews);
        List<ReviewResponseDto> reviews = new LinkedList<>();
        listReviews.sort((review1, review2) -> Integer.compare(review2.getLikeScore(), review1.getLikeScore()));
        for(Review review: listReviews)
        {
            if(!review.getReviewStatus().getName().equals("BLOCKED"))
            {
                ReviewResponseDto responseDto = new ReviewResponseDto();
                BeanUtils.copyProperties(review, responseDto);
                responseDto.setRating(responseDto.getRating() / 10);
                distinguishLikes(user, responseDto, review.getLikes());
                responseDto.setReviewId(review.getId());
                responseDto.setUsername(review.getUser().getUsername());
                responseDto.setFollowedUser(setFollowedUser(user, review));
                reviews.add(responseDto);
            }
        }
        return reviews;
    }

    public List<ReviewResponseDto> mapUsernameReviews(String username, Set<Review> setReviews) throws Exception
    {
        Optional<User> tmp = userRepository.findUserByUsername(username);
        if(tmp.isPresent())
        {
            User user = tmp.get();
            return getReviewResponseDtos(setReviews, user);
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

    private boolean setFollowedUser(User originUser, Review review)
    {
        return originUser.getFollows().contains(review.getUser());
    }
}
