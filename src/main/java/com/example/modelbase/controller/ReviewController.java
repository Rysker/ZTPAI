package com.example.modelbase.controller;

import com.example.modelbase.dto.request.ReviewRequestDto;
import com.example.modelbase.dto.response.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.modelbase.service.ReviewService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController
{
    private final ReviewService reviewService;


    @PostMapping("add/{modelId}")
    public ResponseEntity<MessageResponseDto> addReview(@CookieValue("jwtCookie") String jwtToken, @PathVariable("modelId") Integer modelId, @RequestBody ReviewRequestDto request)
    {
        try
        {
            Integer reviewId = reviewService.addReview(jwtToken, modelId, request);
            return ResponseEntity.ok(new MessageResponseDto(reviewId.toString()));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("like/{id}")
    public ResponseEntity<MessageResponseDto> addLike(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId, @RequestBody Map<String, Boolean> request)
    {
        Boolean isLiked = request.get("liked");
        try
        {
            reviewService.addLike(jwtToken, reviewId, isLiked);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("like/swap/{id}")
    public ResponseEntity<MessageResponseDto> swapLike(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId)
    {
        try
        {
            reviewService.swapLike(jwtToken, reviewId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("like/{id}")
    public ResponseEntity<MessageResponseDto> addLike(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId)
    {
        try
        {
            reviewService.deleteLike(jwtToken, reviewId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("review/{id}")
    public ResponseEntity<MessageResponseDto> deleteReview(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId)
    {
        try
        {
            reviewService.deleteReview(jwtToken, reviewId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
