package com.example.modelbase.controller;

import com.example.modelbase.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController
{
    private final CollectionService collectionService;
    @PostMapping("/add/{id}")
    public ResponseEntity addToColection(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer modelId)
    {
        try
        {
            collectionService.addToCollection(jwtToken, modelId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

