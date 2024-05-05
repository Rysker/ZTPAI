package com.example.modelbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/resource")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class TestController
{
    @GetMapping
    public ResponseEntity<String> sayHello()
    {
        return ResponseEntity.ok("Here is your resource");
    }
}
