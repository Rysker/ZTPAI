package com.example.modelbase.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReviewRequestDto
{
    private String title;
    private String description;
    private BigDecimal score;
}
