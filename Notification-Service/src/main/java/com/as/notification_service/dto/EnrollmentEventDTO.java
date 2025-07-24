package com.as.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEventDTO {
    private Long courseId;
    private String courseTitle;
    private String studentEmail;
    private String studentName;
}

