package com.as.enrollment_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgressResponse {

    private long id;

    private Long userId;
    private Long courseId;
    private Long lectureId;
    private boolean completed;
    private LocalDateTime completedAt;
}
