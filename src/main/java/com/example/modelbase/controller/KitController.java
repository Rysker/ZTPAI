package com.example.modelbase.controller;

import com.example.modelbase.service.ModelKitService;
import lombok.RequiredArgsConstructor;
import com.example.modelbase.dto.response.ModelKitDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/models")
@RequiredArgsConstructor
public class KitController
{
    private final ModelKitService modelKitService;
    @GetMapping("/{vehicle_name}/{id}")
    public ResponseEntity<ModelKitDto> getModelKit(@CookieValue("jwtCookie") String jwtToken, @PathVariable("vehicle_name") String vehicleName, @PathVariable("id") Integer id)
    {
        try
        {
            ModelKitDto kit = modelKitService.getModelKitById(jwtToken, id);
            return new ResponseEntity<>(kit, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
