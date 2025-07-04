package com.as.review_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewResponse {

    private Long id;
    private Long userId;
    private Long courseId;
    private String userName;
    private String courseTitle;
    private String comment;
    private Integer rating;
    private LocalDateTime createdAt;
}
