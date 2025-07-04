package com.as.review_service.controller;

import com.as.review_service.dto.ReviewRequest;
import com.as.review_service.dto.ReviewResponse;
import com.as.review_service.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(
            @RequestBody ReviewRequest request,
            @RequestHeader("Authorization") String token
    ) {
        ReviewResponse response = reviewService.addReview(request, token);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCourse(
            @PathVariable Long courseId,
            @RequestHeader("Authorization") String token
    ) {
        List<ReviewResponse> responses = reviewService.getReviews(courseId, token);
        return ResponseEntity.ok(responses);
    }
}
