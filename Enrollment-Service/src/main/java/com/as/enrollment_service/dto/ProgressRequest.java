package com.as.enrollment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressRequest {

    private Long userId;
    private Long courseId;
    private Long lectureId;
}
