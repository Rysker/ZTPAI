package com.example.modelbase.service;

import com.example.modelbase.dto.response.FilterResponseDto;
import com.example.modelbase.model.Country;
import com.example.modelbase.model.Manufacturer;
import com.example.modelbase.model.Vehicle;
import com.example.modelbase.repository.CountryRepository;
import com.example.modelbase.repository.ManufacturerRepository;
import com.example.modelbase.repository.VariantRepository;
import com.example.modelbase.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService
{
    private final VehicleRepository vehicleRepository;
    private final CountryRepository countryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final VariantRepository variantRepository;

    public List<FilterResponseDto> getVehiclesFilters()
    {
        List<String> generations = vehicleRepository.findDistinctGenerations();
        List<String> countries = new ArrayList<>();
        for(Country x : countryRepository.findAll())
            countries.add(x.getName());

        FilterResponseDto generationFilter = new FilterResponseDto("generation", generations);
        FilterResponseDto countryFilter = new FilterResponseDto("country", countries);

        List<FilterResponseDto> filters = List.of(generationFilter, countryFilter);

        return filters;
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
        List<FilterResponseDto> filters = List.of(manufacturerFilter, variantFilter);

        return filters;
    }
}
