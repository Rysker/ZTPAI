package com.example.modelbase.dto.response;

import lombok.Data;

@Data
public class FollowerResponseDto
{
    private String username;
    private String avatar;
    private String description;
    private Integer followerId;
}
