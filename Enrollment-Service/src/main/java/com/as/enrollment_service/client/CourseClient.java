package com.as.enrollment_service.client;

import com.as.enrollment_service.dto.CourseResponse;
import com.as.enrollment_service.dto.LectureResponse;
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

    @Value("${course.service.url:http://localhost:8081}")
    private String courseUrl;


    private  final RestTemplate restTemplate;

    public CourseResponse getCourseById(Long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // Add token
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<CourseResponse> response = restTemplate.exchange(
                courseUrl + "/api/courses/{id}",
                HttpMethod.GET,
                entity,
                CourseResponse.class,
                id
        );
        return response.getBody();
    }
}
