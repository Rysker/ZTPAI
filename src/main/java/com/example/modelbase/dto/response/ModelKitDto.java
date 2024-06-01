package com.example.modelbase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelKitDto
{
    private Integer id;
    private String photo;
    private String name;
    private String manufacturerCode;
    private String scale;
    private String manufacturer;
    private String variant;
    private String reviewsCount;
    private Double reviewsAverage;
    private String status;
    private boolean isOnWatchlist;

    //Expanded response
    private Set<String> photos;
    private List<ReviewResponseDto> reviews;

    //More expanded response (Collection view)
    private String isPublic;
    private LocalDate completionDate;
    private Integer collectibleId;
}
