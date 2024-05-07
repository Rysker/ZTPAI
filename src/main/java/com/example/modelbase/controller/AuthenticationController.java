package com.example.modelbase.controller;

import com.example.modelbase.dto.request.SignInRequest;
import com.example.modelbase.dto.request.SignUpRequest;
import com.example.modelbase.dto.response.JwtAuthenticationResponse;
import com.example.modelbase.service.AuthenticationService;

import com.example.modelbase.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController
{
    private final AuthenticationService authenticationService;

    private final JwtService jwtService;
    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequest request)
    {
        try
        {
            authenticationService.signUp(request);
            return "User registered successfully!";
        }
        catch (DataIntegrityViolationException e)
        {
            if (e.getMessage().contains("constraint_name"))
                return "Email or username already exists.";
             else
                return "Invalid data";
        }
        catch(IllegalArgumentException e)
        {
            return e.getMessage();
        }
        catch(Exception e)
        {
            return "Unknown error!";
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInRequest request, HttpServletResponse res)
    {
        JwtAuthenticationResponse token = authenticationService.signIn(request);
        Cookie jwtCookie = new Cookie("jwtCookie", token.getToken());
        jwtCookie.setPath("/");
        jwtCookie.setHttpOnly(true);
        jwtCookie.setMaxAge(36000);
        res.addCookie(jwtCookie);
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkAuthentication()
    {
        boolean isAuthenticated = jwtService.checkAuthenticationStatus();
        return ResponseEntity.ok(isAuthenticated);
    }
}
