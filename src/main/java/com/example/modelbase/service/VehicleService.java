package com.example.modelbase.service;

import com.example.modelbase.dto.response.ModelKitDto;
import com.example.modelbase.dto.response.VehicleInfoDto;
import com.example.modelbase.mapper.ModelKitMapper;
import com.example.modelbase.model.*;
import com.example.modelbase.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService
{
    private final VehicleRepository vehicleRepository;
    private final ModelKitMapper modelKitMapper;

    public List<VehicleInfoDto> getAllVehicleInfo()
    {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<VehicleInfoDto> getVehicleInfoByType(String vehicleType)
    {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .filter(vehicle -> (vehicle.getVehicleType().getName().trim()).equals(vehicleType))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<ModelKitDto> getVehicleKits(String token, String vehicle_name) throws Exception
    {
        Vehicle vehicle = vehicleRepository.getVehicleByName(vehicle_name);
        Set<ModelKit> kits = new LinkedHashSet<>();

        for(Variant variant: vehicle.getVariants())
            kits.addAll(variant.getModelKits());

        return kits.stream()
                .map(x -> modelKitMapper.kitShortMap(token, x))
                .collect(Collectors.toList());
    }

    private VehicleInfoDto convertToDto(Vehicle vehicle)
    {
        VehicleInfoDto vehicleInfoDto = new VehicleInfoDto();
        BeanUtils.copyProperties(vehicle, vehicleInfoDto);
        vehicleInfoDto.setVehicleType(vehicle.getVehicleType().getName());
        vehicleInfoDto.setCountry(vehicle.getCountry().getName());
        return vehicleInfoDto;
    }
}
