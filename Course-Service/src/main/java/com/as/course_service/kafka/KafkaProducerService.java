package com.as.course_service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    @Value("${kafka.topic.course}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendCourseCreatedNotification(String message) {
        kafkaTemplate.send(topic, message);
    }
}
