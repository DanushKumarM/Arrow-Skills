package com.as.course_service.client;

import com.as.course_service.dto.InstructorResponseDTO;
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
public class InstructorClient {

    @Value("${user.service.url:http://localhost:8080}")
    private String userServiceUrl;

    private final RestTemplate restTemplate;



    public String getInstructorName(Long instructorId, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = userServiceUrl + "/api/users/instructor/" + instructorId;

        ResponseEntity<InstructorResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                InstructorResponseDTO.class
        );

        return response.getBody() != null ? response.getBody().getName() : "Unknown";
    }
}
