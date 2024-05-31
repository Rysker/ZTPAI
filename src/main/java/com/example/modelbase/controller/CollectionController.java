package com.example.modelbase.controller;

import com.example.modelbase.dto.request.DateChangeDto;
import com.example.modelbase.dto.response.CollectionResponseDto;
import com.example.modelbase.dto.response.CollectionStatisticsDto;
import com.example.modelbase.dto.response.MessageResponseDto;
import com.example.modelbase.service.CollectibleService;
import com.example.modelbase.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.stat.CollectionStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/collection")
public class CollectionController
{
    private final CollectionService collectionService;
    private final CollectibleService collectibleService;
    @PostMapping("/collectible/add/{id}")
    public ResponseEntity<MessageResponseDto> addToCollection(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer modelId)
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

    @GetMapping("/{username}")
    public ResponseEntity<CollectionResponseDto> getUserCollection(@CookieValue("jwtCookie") String jwtToken, @PathVariable("username") String username)
    {
        try
        {
            CollectionResponseDto response = collectionService.getUserCollection(jwtToken, username);
            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/collectible/visibility/{id}")
    public ResponseEntity<MessageResponseDto> changeVisibility(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer collectibleId)
    {
        try
        {
            collectionService.changeVisibility(jwtToken, collectibleId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/collectible/delete/{id}")
    public ResponseEntity<MessageResponseDto> deleteCollectible(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer collectibleId)
    {
        try
        {
            collectibleService.deleteCollectible(jwtToken, collectibleId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/collectible/edit/{id}")
    public ResponseEntity<MessageResponseDto> editCollectible(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer collectibleId, @RequestBody DateChangeDto date)
    {
        try
        {
            LocalDate date1 = LocalDate.parse(date.getCompletionDate());
            collectibleService.changeDate(jwtToken, collectibleId, date1);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/collectible/finish/{id}")
    public ResponseEntity<MessageResponseDto> finishCollectible(@CookieValue("jwtCookie") String jwtToken, @PathVariable("id") Integer collectibleId)
    {
        try
        {
            collectibleService.changeProgress(jwtToken, collectibleId);
            return ResponseEntity.ok(new MessageResponseDto("Success"));
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

