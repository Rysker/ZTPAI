package com.example.modelbase.controller;

import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportController
{
    private final ReportService reportService;

    @PostMapping("review/{id}")
    public ResponseEntity<MessageResponseDto> addReport(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer reviewId)
    {
        try
        {
            reportService.addReport(jwtToken, reviewId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(new MessageResponseDto("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
