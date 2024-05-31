package com.example.modelbase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionStatisticsDto
{
    private String country;
    private Integer count;
}
