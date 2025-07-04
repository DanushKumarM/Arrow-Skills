package com.as.review_service.service;

import com.as.review_service.dto.ReviewRequest;
import com.as.review_service.dto.ReviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewService {

    ReviewResponse addReview(ReviewRequest request, String token);

    List<ReviewResponse> getReviews(Long courseId, String token);

}
