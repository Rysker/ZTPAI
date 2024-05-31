package com.example.modelbase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto
{
    private Integer reviewId;
    private String mainReason;
    private Integer reportCount;
    private String reviewContent;
}
