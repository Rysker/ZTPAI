package com.example.modelbase.controller;

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

    @PostMapping("like/{id}")
    public ResponseEntity addLike(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId, @RequestBody Map<String, Boolean> request)
    {
        Boolean isLiked = request.get("liked");
        try
        {
            reviewService.addLike(jwtToken, reviewId, isLiked);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("like/swap/{id}")
    public ResponseEntity swapLike(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId)
    {
        try
        {
            reviewService.swapLike(jwtToken, reviewId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("like/{id}")
    public ResponseEntity addLike(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId)
    {
        try
        {
            reviewService.deleteLike(jwtToken, reviewId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
