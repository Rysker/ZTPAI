package com.example.modelbase.controller;

import com.example.modelbase.dto.response.ModelKitShortDto;
import com.example.modelbase.dto.response.VehicleInfoDto;
import java.util.List;
import java.util.Set;

import com.example.modelbase.service.JwtService;
import com.example.modelbase.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController
{
    private final VehicleService vehicleService;
    private final JwtService jwtService;

    @GetMapping("/all")
    public ResponseEntity<List<VehicleInfoDto>> getAllVehicleInfo(@CookieValue("jwtCookie") String jwtToken)
    {
        List<VehicleInfoDto> vehicles = vehicleService.getAllVehicleInfo();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<VehicleInfoDto>> getVehicleInfoByType(@CookieValue("jwtCookie") String jwtToken, @RequestParam("type") String vehicleType)
    {
        List<VehicleInfoDto> vehicles = vehicleService.getVehicleInfoByType(vehicleType);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/models/{vehicle_name}")
    public ResponseEntity<Set<ModelKitShortDto>> getModelKits(@CookieValue("jwtCookie") String jwtToken, @PathVariable("vehicle_name") String vehicleName)
    {
        Set<ModelKitShortDto> kits = vehicleService.getVehicleKits(jwtToken, vehicleName);
        return new ResponseEntity<>(kits, HttpStatus.OK);
    }

}
