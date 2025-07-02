package com.as.enrollment_service.mapper;

import com.as.enrollment_service.dto.ProgressRequest;
import com.as.enrollment_service.dto.ProgressResponse;
import com.as.enrollment_service.model.Progress;
import org.springframework.stereotype.Component;

@Component
public class ProgressMapper {

    public Progress toEntity (ProgressRequest request){
        return Progress.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .lectureId(request.getLectureId())
                .build();
    }

    public ProgressResponse toResponse (Progress progress){
        return ProgressResponse.builder()
                .id(progress.getId())
                .userId(progress.getUserId())
                .courseId(progress.getCourseId())
                .lectureId(progress.getLectureId())
                .completed(progress.isCompleted())
                .completedAt(progress.getCompletedAt())
                .build();
    }
}
