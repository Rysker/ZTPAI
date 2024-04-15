package com.example.modelbase.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
public class KitController
{

    @GetMapping("/{modelId}")
    public ResponseEntity<String> getModel(@PathVariable Long modelId)
    {
        return null;
    }

    @PostMapping("/{modelId}/add-vehicle")
    public ResponseEntity<String> addVehicleToCollection(@PathVariable Long modelId)
    {
        return null;
    }

    @PostMapping("/{modelId}/review")
    public ResponseEntity<String> writeReview(@PathVariable Long modelId, @RequestBody String reviewDTO)
    {
        return null;
    }

    @PostMapping("/reviews/{reviewId}/rate")
    public ResponseEntity<String> rateReview(@PathVariable Long reviewId, @RequestParam int rating)
    {
        return null;
    }

    @PostMapping("/reviews/{reviewId}/report")
    public ResponseEntity<String> reportReview(@PathVariable Long reviewId)
    {
       return null;
    }
}
