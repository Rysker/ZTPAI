package com.example.modelbase.service;

import com.example.modelbase.dto.response.ModelKitDto;
import com.example.modelbase.mapper.ModelKitMapper;
import com.example.modelbase.model.ModelKit;
import com.example.modelbase.repository.ModelKitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModelKitService
{
    private final ModelKitRepository modelKitRepository;
    private final ModelKitMapper modelKitMapper;
    public ModelKitDto getModelKitByVehicleNameAndId(String token, String vehicleName, Integer id) throws Exception
    {
        Optional<ModelKit> kit = modelKitRepository.findById(id);
        if(kit.isPresent())
            return modelKitMapper.kitLongMap(token, kit.get());
        else
            return null;
    }
}
