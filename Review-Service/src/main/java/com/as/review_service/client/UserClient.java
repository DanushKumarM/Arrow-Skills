package com.as.review_service.client;

import com.as.review_service.dto.UserResponse;
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
public class UserClient {

    private final RestTemplate restTemplate;

    @Value("${user.service.url:http://localhost:8080}")
    private String userUrl;

    public UserResponse getUserById(Long userId, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = userUrl + "/api/users/{id}";

        ResponseEntity<UserResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                UserResponse.class,
                userId
        );

        return response.getBody();
    }
}
