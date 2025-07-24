package com.as.notification_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEvent {

    private Long userId;
    private String toEmail;
    private String subject;
    private String message;
}
