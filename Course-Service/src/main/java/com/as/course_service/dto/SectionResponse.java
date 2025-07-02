package com.as.course_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionResponse {
    private Long id;
    private String title;
    private List<LectureResponse> lectures;
}

