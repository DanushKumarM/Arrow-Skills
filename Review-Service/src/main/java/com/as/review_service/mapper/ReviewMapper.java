package com.as.review_service.mapper;

import com.as.review_service.dto.ReviewResponse;
import com.as.review_service.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewResponse toDto(Review review, String userName, String courseTitle) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .userName(userName)
                .courseId(review.getCourseId())
                .courseTitle(courseTitle)
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public Review toEntity(ReviewResponse response) {
        return Review.builder()
                .id(response.getId())
                .userId(response.getUserId())
                .courseId(response.getCourseId())
                .comment(response.getComment())
                .rating(response.getRating())
                .createdAt(response.getCreatedAt())
                .build();
    }
}
