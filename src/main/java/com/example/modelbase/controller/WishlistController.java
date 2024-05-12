package com.example.modelbase.controller;

import com.example.modelbase.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/watchlist")
@RequiredArgsConstructor

public class WishlistController
{
    private final WishlistService wishlistService;

    @PostMapping("/change/{id}")
    public ResponseEntity changeObserved(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer kitId)
    {
        try
        {
            wishlistService.changeWishlist(jwtToken, kitId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
