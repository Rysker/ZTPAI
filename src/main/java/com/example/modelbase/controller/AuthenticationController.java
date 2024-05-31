package com.example.modelbase.controller;

import com.example.modelbase.dto.request.SignInRequest;
import com.example.modelbase.dto.request.SignUpRequest;
import com.example.modelbase.dto.response.JwtAuthenticationResponse;
import com.example.modelbase.dto.response.LoginResponseDto;
import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.model.User;
import com.example.modelbase.service.AuthenticationService;
import com.example.modelbase.service.JwtService;
import com.example.modelbase.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController
{
    private final AuthenticationService authenticationService;
    private final UserService userService;

    private final JwtService jwtService;
    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(@RequestBody SignUpRequest request)
    {
        try
        {
            authenticationService.signUp(request);
            return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
        }
        catch (DataIntegrityViolationException e)
        {
            if (e.getMessage().contains("constraint_name"))
                return  ResponseEntity.ok(new MessageResponseDto("Email or username already exists."));
            else
                return ResponseEntity.ok(new MessageResponseDto("Invalid data"));
        }
        catch(IllegalArgumentException e)
        {
            return ResponseEntity.ok(new MessageResponseDto(e.getMessage()));
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("UnknownError!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> signin(@RequestBody SignInRequest request, HttpServletResponse res)
    {
        try
        {
            JwtAuthenticationResponse response = authenticationService.signIn(request);
            Cookie jwtCookie = new Cookie("jwtCookie", response.getToken());
            jwtCookie.setPath("/");
            jwtCookie.setHttpOnly(true);
            jwtCookie.setMaxAge(3600);
            res.addCookie(jwtCookie);
            User user = userService.getUserFromToken(response.getToken());
            return ResponseEntity.ok(new LoginResponseDto("Success", user.getUsername(), List.of(user.getAccountType().getName())));
        }
        catch(Exception e)
        {
            LoginResponseDto responseDto = new LoginResponseDto();
            responseDto.setMessage(e.getMessage());
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkAuthentication(@CookieValue("jwtCookie") String jwtToken)
    {
        boolean isAuthenticated = jwtService.checkAuthenticationStatus(jwtToken);
        return isAuthenticated ? ResponseEntity.ok(true) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response)
    {
        Cookie authCookie = new Cookie("jwtCookie", null);
        authCookie.setPath("/");
        authCookie.setMaxAge(0);
        response.addCookie(authCookie);
    }
}
