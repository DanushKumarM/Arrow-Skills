package com.as.enrollment_service.service;

import com.as.enrollment_service.dto.EnrollmentResponse;
import com.as.enrollment_service.model.Enrollment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentService {

    EnrollmentResponse enroll(Long userId, Long courseId, String token);
    List<EnrollmentResponse> getEnrollmentByUser(Long id,String token);
    boolean isEnrolled(Long userId, Long courseId,String token);

}
