package com.example.modelbase.controller;

import com.example.modelbase.dto.response.FilterResponseDto;
import com.example.modelbase.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filters")
@RequiredArgsConstructor
public class FilterController
{
    private final FilterService filterService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<FilterResponseDto>> getVehicleFilters(@CookieValue("jwtCookie") String jwtToken)
    {
        try
        {
            List<FilterResponseDto> filters = filterService.getVehiclesFilters();
            return new ResponseEntity<>(filters, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("vehicles/{vehicle_name}")
    public ResponseEntity<List<FilterResponseDto>> getKitsFilters(@CookieValue("jwtCookie") String jwtToken, @PathVariable("vehicle_name") String vehicle_name)
    {
        try
        {
            List<FilterResponseDto> filters = filterService.getKitsFilters(vehicle_name);
            return new ResponseEntity<>(filters, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping ("collection")
    public ResponseEntity<List<FilterResponseDto>> getCollectionFilters(@CookieValue("jwtCookie") String jwtToken)
    {
        try
        {
            List<FilterResponseDto> filters = filterService.getCollectionFilters();
            return new ResponseEntity<>(filters, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
