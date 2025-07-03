package com.as.enrollment_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class SectionResponse {
    private Long id;
    private String title;
    private List<LectureResponse> lectures;
}
