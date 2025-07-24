package com.as.enrollment_service.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.enrollment}")
    private String topic;

    public  void sendEnrollmentNotification(String message){
        kafkaTemplate.send(topic, message);
    }

}
