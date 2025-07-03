package com.as.enrollment_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String categoryName;
    private Long instructorId;
    private String instructorName;
    private List<SectionResponse> sections;
}
