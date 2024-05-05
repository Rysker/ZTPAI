package com.example.modelbase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleInfoDto
{
    Integer id;
    String name;
    String vehicleType;
    String vehiclePhoto;
    String generation;
    String country;
}
