package com.as.enrollment_service.client;

import com.as.enrollment_service.dto.UserResponse;
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

    @Value("${user.service.url:http://localhost:8080}")
        private String userServiceUrl;

    private final RestTemplate restTemplate;

    public UserResponse getUserId(Long userId, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        String url = userServiceUrl + "/api/users/{id}";

        ResponseEntity<UserResponse> userResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                UserResponse.class,
                userId
        );
        return userResponse.getBody();
    }
}
