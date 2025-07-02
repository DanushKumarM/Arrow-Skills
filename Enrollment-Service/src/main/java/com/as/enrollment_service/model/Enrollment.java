package com.as.enrollment_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private long courseId;

    private LocalDateTime enrolledAt;

    @PrePersist
    public void onEnroll() {
        this.enrolledAt = LocalDateTime.now();
    }
}
