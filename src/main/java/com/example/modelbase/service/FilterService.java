package com.example.modelbase.service;

import com.example.modelbase.dto.response.FilterResponseDto;
import com.example.modelbase.model.Country;
import com.example.modelbase.model.Manufacturer;
import com.example.modelbase.model.Progress;
import com.example.modelbase.model.Vehicle;
import com.example.modelbase.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService
{
    private final VehicleRepository vehicleRepository;
    private final CountryRepository countryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final VariantRepository variantRepository;
    private final ProgressRepository progressRepository;

    public List<FilterResponseDto> getVehiclesFilters()
    {
        List<String> generations = vehicleRepository.findDistinctGenerations();
        List<String> countries = new ArrayList<>();
        for(Country x : countryRepository.findAll())
            countries.add(x.getName());

        FilterResponseDto generationFilter = new FilterResponseDto("generation", generations);
        FilterResponseDto countryFilter = new FilterResponseDto("country", countries);

        return List.of(generationFilter, countryFilter);
    }

    public List<FilterResponseDto> getKitsFilters(String vehicle_name)
    {
        Vehicle vehicle = vehicleRepository.getVehicleByName(vehicle_name);
        List<String> variants = variantRepository.findDistinctByVehicleId(vehicle.getId());
        List<String> manufacturers = new ArrayList<>();
        for(Manufacturer x : manufacturerRepository.findAll())
            manufacturers.add(x.getName());

        FilterResponseDto manufacturerFilter = new FilterResponseDto("manufacturer", manufacturers);
        FilterResponseDto variantFilter = new FilterResponseDto("variant", variants);

        return List.of(manufacturerFilter, variantFilter);
    }

    public List<FilterResponseDto> getCollectionFilters()
    {
        List<String> progressStatus = new LinkedList<>();
        List<String> visibility = List.of("Public", "Hidden");
        for(Progress prog : progressRepository.findAll())
            progressStatus.add(prog.getName());

        FilterResponseDto visibilityFilter = new FilterResponseDto("visibility", visibility);
        FilterResponseDto progressFilter = new FilterResponseDto("progress", progressStatus);

        return List.of(visibilityFilter, progressFilter);
    }
}
