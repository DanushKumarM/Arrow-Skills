package com.as.review_service.service;

import com.as.review_service.client.CourseClient;
import com.as.review_service.client.UserClient;
import com.as.review_service.dto.CourseResponse;
import com.as.review_service.dto.ReviewRequest;
import com.as.review_service.dto.ReviewResponse;
import com.as.review_service.dto.UserResponse;
import com.as.review_service.mapper.ReviewMapper;
import com.as.review_service.model.Review;
import com.as.review_service.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl  implements  ReviewService {

    private final ReviewRepository reviewRepository;
    private  final CourseClient courseClient;
    private final UserClient userClient;
    private final ReviewMapper reviewMapper;

    @Override
    public ReviewResponse addReview(ReviewRequest request, String token) {

        UserResponse user = userClient.getUserById(request.getUserId(), token);
        CourseResponse course = courseClient.getCourseById(request.getCourseId(),token);


        Review review = Review.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .comment(request.getComment())
                .rating(request.getRating())
                .build();
        Review savedReview = reviewRepository.save(review);

        return reviewMapper.toDto(savedReview,user.getName(),course.getTitle());
    }

    @Override
    public List<ReviewResponse> getReviews(Long courseId, String token) {
        List<Review> reviews = reviewRepository.findByCourseId(courseId);
        List<ReviewResponse> responses = new ArrayList<>();

        for (Review review : reviews) {
            UserResponse user = userClient.getUserById(review.getUserId(), token);
            CourseResponse course = courseClient.getCourseById(review.getCourseId(), token);

            ReviewResponse response = reviewMapper.toDto(review, user.getName(), course.getTitle());
            responses.add(response);
        }

        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Missing or invalid Authorization header");
        }


        return responses;
    }
}
