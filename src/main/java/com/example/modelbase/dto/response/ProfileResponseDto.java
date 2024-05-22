package com.example.modelbase.dto.response;

import lombok.Data;

@Data
public class ProfileResponseDto
{
    private String username;
    private String description;
    private String isSameUser;
    private String isFollowed;
    private Integer reviewsCount;
    private Integer reputation;
    private String memberSince;
    private ProfileCollectionDto profileCollection;
}
