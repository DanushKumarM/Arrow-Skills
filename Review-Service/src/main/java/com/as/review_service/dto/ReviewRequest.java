package com.as.review_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewRequest {

    private Long userId;
    private Long courseId;
    private String comment;
    private Integer rating;
}
