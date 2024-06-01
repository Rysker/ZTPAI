package com.example.modelbase.service;

import com.example.modelbase.dto.response.FilterResponseDto;
import com.example.modelbase.model.*;
import com.example.modelbase.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FilterService
{
    private final VehicleRepository vehicleRepository;
    private final CountryRepository countryRepository;
    private final VariantRepository variantRepository;
    private final ProgressRepository progressRepository;

    public List<FilterResponseDto> getVehiclesFilters()
    {
        List<String> generations = vehicleRepository.findDistinctGenerations();
        List<String> countries = new ArrayList<>();
        List<FilterResponseDto> filters = new LinkedList<>();
        for(Country x : countryRepository.findAll())
            countries.add(x.getName());

        if(!generations.isEmpty())
            filters.add(new FilterResponseDto("generation", generations));
        if(!countries.isEmpty())
            filters.add(new FilterResponseDto("country", countries));

        return filters;
    }

    public List<FilterResponseDto> getKitsFilters(String vehicle_name)
    {
        Vehicle vehicle = vehicleRepository.getVehicleByName(vehicle_name);
        List<Variant> variants1 = List.copyOf(vehicle.getVariants());
        List<String> variants = variantRepository.findDistinctByVehicleId(vehicle.getId());
        Set<String> manufacturers = new TreeSet<>();
        List<FilterResponseDto> filters = new LinkedList<>();

        for(Variant variant: variants1)
        {
            for(ModelKit x : variant.getModelKits())
                manufacturers.add(x.getManufacturer().getName());
        }
        if(!manufacturers.isEmpty())
            filters.add(new FilterResponseDto("manufacturer", List.copyOf(manufacturers)));
        if(!variants.isEmpty())
            filters.add(new FilterResponseDto("variant", variants));

        return filters;
    }

    public List<FilterResponseDto> getCollectionFilters()
    {
        List<String> progressStatus = new LinkedList<>();
        List<String> visibility = List.of("Public", "Hidden");
        for(Progress progress : progressRepository.findAll())
            progressStatus.add(progress.getName());

        FilterResponseDto visibilityFilter = new FilterResponseDto("visibility", visibility);
        FilterResponseDto progressFilter = new FilterResponseDto("progress", progressStatus);

        return List.of(visibilityFilter, progressFilter);
    }
}
