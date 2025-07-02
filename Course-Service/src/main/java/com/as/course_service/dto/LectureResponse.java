package com.as.course_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LectureResponse {

    private Long id;
    private String title;
    private String url;
    private Long courseId;
}

