package com.as.enrollment_service.client;

import com.as.enrollment_service.dto.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class LectureClient {

    @Value("${course.service.url:http://localhost:8081}")
    private String lectureUrl;

    private  final RestTemplate restTemplate;

    public LectureResponse getLectureById(Long id) {
        ResponseEntity<LectureResponse> response = restTemplate.getForEntity(
                lectureUrl + "/api/lectures/{id}",
                LectureResponse.class,
                id
        );
        return response.getBody();
    }
}
