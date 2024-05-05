package com.example.modelbase.controller;

import com.example.modelbase.dto.response.FilterResponseDto;
import com.example.modelbase.repository.CountryRepository;
import com.example.modelbase.repository.VehicleRepository;
import com.example.modelbase.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Filter;

@RestController
@RequestMapping("/api/v1/filters")
@RequiredArgsConstructor
public class FilterContoller
{
    private final FilterService filterService;

    @GetMapping("/vehicles")
    public ResponseEntity<List<FilterResponseDto>> getVehicleFilters()
    {
        List<FilterResponseDto> filters = filterService.getVehiclesFilters();
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }

    @GetMapping("vehicles/{vehicle_name}")
    public ResponseEntity<List<FilterResponseDto>> getKitsFilters(@PathVariable("vehicle_name") String vehicle_name)
    {
        List<FilterResponseDto> filters = filterService.getKitsFilters(vehicle_name);
        return new ResponseEntity<>(filters, HttpStatus.OK);
    }
}
