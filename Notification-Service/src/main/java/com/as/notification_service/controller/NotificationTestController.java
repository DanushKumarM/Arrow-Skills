package com.as.notification_service.controller;

import com.as.notification_service.dto.NotificationEvent;
import com.as.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationTestController {

    private final NotificationService notificationService;

    @PostMapping("/test")
    public ResponseEntity<String> sendTestNotification(@RequestBody NotificationEvent event) {
        notificationService.sendNotification(event);
        return ResponseEntity.ok("Email notification triggered successfully!");
    }
}

