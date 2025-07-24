package com.as.notification_service.kafka;

import com.as.notification_service.dto.NotificationEvent;
import com.as.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "course-topic",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleCourse(NotificationEvent event) {
        System.out.println("📩 Received New Course Event");
        notificationService.sendNotification(event);
    }

    @KafkaListener(
            topics = "enrollment-topic",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handleEnrollment(NotificationEvent event) {
        System.out.println("📩 Received Enrollment Event");
        notificationService.sendNotification(event);
    }
}
