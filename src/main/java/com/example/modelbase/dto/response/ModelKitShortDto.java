package com.example.modelbase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelKitShortDto
{
    private String photo;
    private String name;
    private String manufacturerCode;
    private String scale;
    private String manufacturer;
    private String variant;
    private String reviewsCount;
    private String reviewsAverage;
    private String status;
    private boolean isOnWatchlist;
}
