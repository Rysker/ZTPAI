package com.example.modelbase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto
{
    private String username;
    private Integer reviewId;
    private String title;
    private String content;
    private Integer rating;
    private LocalDate writeDate;
    private Integer score;
    private String userLike;
    private boolean followedUser;

}
