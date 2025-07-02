package com.as.course_service.dto;

import com.as.course_service.enums.CourseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private Long id;
    private String title;
    private String description;
    private CourseType courseType;
    private String thumbnailUrl;
    private String contentUrl;
    private Double price;
    private String categoryName;
    private Long instructorId;
    private String instructorName;
    private String createdAt;
}
