package com.example.modelbase.controller;

import com.example.modelbase.dto.response.ModelKitDto;
import com.example.modelbase.dto.response.VehicleInfoDto;
import java.util.List;
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
    public ResponseEntity<List<VehicleInfoDto>> getAllVehicleInfo(@CookieValue("jwtCookie") String jwtToken)
    {
        try
        {
            List<VehicleInfoDto> vehicles = vehicleService.getAllVehicleInfo();
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/type")
    public ResponseEntity<List<VehicleInfoDto>> getVehicleInfoByType(@CookieValue("jwtCookie") String jwtToken, @RequestParam("type") String vehicleType)
    {
        try
        {
            List<VehicleInfoDto> vehicles = vehicleService.getVehicleInfoByType(vehicleType);
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/models/{vehicle_name}")
    public ResponseEntity<List<ModelKitDto>> getModelKits(@CookieValue("jwtCookie") String jwtToken, @PathVariable("vehicle_name") String vehicleName)
    {
        try
        {
            List<ModelKitDto> kits = vehicleService.getVehicleKits(jwtToken, vehicleName);
            return new ResponseEntity<>(kits, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
