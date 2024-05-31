package com.example.modelbase.controller;

import com.example.modelbase.dto.response.FollowerResponseDto;
import com.example.modelbase.service.FollowersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/followers")
public class FollowersController
{
    private final FollowersService followersService;

    @GetMapping()
    public ResponseEntity<List<FollowerResponseDto>> getFollowers(@CookieValue("jwtCookie") String jwtToken)
    {
        try
        {
            List<FollowerResponseDto> response = followersService.getFollowers(jwtToken);
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
