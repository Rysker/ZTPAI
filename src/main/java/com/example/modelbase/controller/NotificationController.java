package com.example.modelbase.controller;

import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController
{
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<MessageResponseDto> pollNotification(@CookieValue("jwtCookie") String jwtToken) throws InterruptedException
    {
        try
        {
            MessageResponseDto notification = new MessageResponseDto(notificationService.getNotification(jwtToken));
            return ResponseEntity.ok(notification);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}