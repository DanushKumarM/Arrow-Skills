package com.as.enrollment_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {

    private long id;

    private long userId;

    private long courseId;

    private LocalDateTime enrolledAt;
}
