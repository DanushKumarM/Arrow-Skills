package com.as.review_service.client;

import com.as.review_service.dto.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CourseClient {

    private final RestTemplate restTemplate;

    @Value("${course.service.url:http://localhost:8081}")
    private String courseServiceUrl;

    public CourseResponse getCourseById(Long courseId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = courseServiceUrl + "/api/courses/{id}";

        ResponseEntity<CourseResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                CourseResponse.class,
                courseId
        );
        return response.getBody();
    }
}
