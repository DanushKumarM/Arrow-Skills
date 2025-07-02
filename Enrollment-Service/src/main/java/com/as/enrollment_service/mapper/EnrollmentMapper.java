package com.as.enrollment_service.mapper;

import com.as.enrollment_service.dto.EnrollmentRequest;
import com.as.enrollment_service.dto.EnrollmentResponse;
import com.as.enrollment_service.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequest request) {
        return Enrollment.builder()
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .build();
    }

    public EnrollmentResponse toResponse(Enrollment enrollment) {
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .userId(enrollment.getUserId())
                .courseId(enrollment.getCourseId())
                .enrolledAt(enrollment.getEnrolledAt())
                .build();
    }
}
