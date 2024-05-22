package com.example.modelbase.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProfileCollectionDto
{
    private Integer id;
    private Integer count;
    private List<SmallCollectibleDto> collectibles;
}
