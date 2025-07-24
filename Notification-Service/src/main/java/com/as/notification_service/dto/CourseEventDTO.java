package com.as.notification_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseEventDTO {
    private Long courseId;
    private String title;
    private String instructorEmail;
    private String instructorName;
}

