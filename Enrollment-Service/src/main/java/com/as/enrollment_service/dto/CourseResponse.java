package com.as.enrollment_service.dto;

import lombok.Data;

@Data
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private Long instructorId;
    private String instructorName;
}
