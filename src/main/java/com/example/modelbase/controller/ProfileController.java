package com.example.modelbase.controller;

import com.example.modelbase.dto.request.DescriptionChangeDto;
import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.dto.response.ProfileResponseDto;
import com.example.modelbase.dto.response.RolesResponseDto;
import com.example.modelbase.service.ProfileService;
import com.example.modelbase.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class ProfileController
{
    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponseDto> getProfile(@CookieValue("jwtCookie") String jwtToken, @PathVariable("username") String username)
    {
        try
        {
            ProfileResponseDto response = profileService.getProfile(jwtToken, username);
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("follow/{username}")
    public ResponseEntity<MessageResponseDto> changeFollow(@CookieValue("jwtCookie") String jwtToken, @PathVariable("username") String username)
    {
        try
        {
            profileService.changeFollow(jwtToken, username);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("description")
    public ResponseEntity<MessageResponseDto> changeDescription(@CookieValue("jwtCookie") String jwtToken, @RequestBody DescriptionChangeDto request)
    {
        try
        {
            profileService.changeDescription(jwtToken, request);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("roles")
    public ResponseEntity<RolesResponseDto> getRoles(@CookieValue("jwtCookie") String jwtToken)
    {
        try
        {
            RolesResponseDto response = userService.getRolesFromToken(jwtToken);
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
