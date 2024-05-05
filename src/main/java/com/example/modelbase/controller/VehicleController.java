package com.example.modelbase.controller;

import com.example.modelbase.dto.response.ModelKitShortDto;
import com.example.modelbase.dto.response.VehicleInfoDto;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/all")
    public ResponseEntity<List<VehicleInfoDto>> getAllVehicleInfo()
    {
        List<VehicleInfoDto> vehicles = vehicleService.getAllVehicleInfo();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/type")
    public ResponseEntity<List<VehicleInfoDto>> getVehicleInfoByType(@RequestParam("type") String vehicleType)
    {
        List<VehicleInfoDto> vehicles = vehicleService.getVehicleInfoByType(vehicleType);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/models/{vehicle_name}")
    public ResponseEntity<Set<ModelKitShortDto>> getModelKits(@RequestHeader("Authorization") String bearerToken, @PathVariable("vehicle_name") String vehicleName)
    {
        String token = bearerToken.replace("Bearer", "").trim();
        Set<ModelKitShortDto> kits = vehicleService.getVehicleKits(token, vehicleName);
        return new ResponseEntity<>(kits, HttpStatus.OK);
    }

}
