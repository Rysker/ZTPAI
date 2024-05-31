package com.example.modelbase.controller;

import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.dto.response.ReportResponseDto;
import com.example.modelbase.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReportResponseDto>> getReports(@CookieValue("jwtCookie") String jwtToken)
    {
        try
        {
            List<ReportResponseDto> response = reportService.getAllReports();
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("review/verify/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponseDto> verifyReport(@CookieValue("jwtCookie") String jwtToken,
                                                           @PathVariable("id") Integer reviewId,
                                                           @RequestBody MessageResponseDto request)
    {
        try
        {
            reportService.verifyReport(reviewId, request);
            return ResponseEntity.ok(new MessageResponseDto("Action performed!"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
