package com.as.enrollment_service.service;

import com.as.enrollment_service.dto.ProgressRequest;
import com.as.enrollment_service.dto.ProgressResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProgressService {

    ProgressResponse markProgress(Long userId, Long lectureId, String token);

    List<ProgressResponse> getProgressByUserAndCourse(Long userId, Long courseId,String token);
}
