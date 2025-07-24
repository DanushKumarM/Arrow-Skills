package com.as.notification_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String toEmail;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String message;

    private boolean success;
    private String errorMessage;
    private LocalDateTime sentAt;

    @PrePersist
    public void setSentAt() {
        this.sentAt = LocalDateTime.now();
    }
}
