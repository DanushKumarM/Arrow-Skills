package com.as.enrollment_service.service;

import com.as.enrollment_service.client.CourseClient;
import com.as.enrollment_service.client.UserClient;
import com.as.enrollment_service.dto.CourseResponse;
import com.as.enrollment_service.dto.EnrollmentResponse;

import com.as.enrollment_service.dto.UserResponse;
import com.as.enrollment_service.enums.Role;
import com.as.enrollment_service.mapper.EnrollmentMapper;
import com.as.enrollment_service.model.Enrollment;
import com.as.enrollment_service.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final UserClient userClient;
    private final CourseClient courseClient;
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse enroll(Long userId, Long courseId, String token) {
        UserResponse user = userClient.getUserId(userId,token);
        if(user.getRole() != Role.STUDENT ){
            throw new RuntimeException("Only Students can Enroll");
        }
        CourseResponse course = courseClient.getCourseById(courseId,token);
        EnrollmentResponse response = new EnrollmentResponse();

        Optional<Enrollment> existing = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if(existing.isPresent()){
            throw new RuntimeException("Already enrolled in this course");
        }
        Enrollment enrollmentResponse = new Enrollment();
        enrollmentResponse.setUserId(userId);
        enrollmentResponse.setCourseId(courseId);
        enrollmentResponse.setEnrolledAt(LocalDateTime.now());

        Enrollment enrollment = enrollmentRepository.save(enrollmentResponse);
        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getEnrollmentByUser(Long userId,String token) {
        UserResponse user = userClient.getUserId(userId, token);

        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);
        List<EnrollmentResponse> enrollmentResponses = new ArrayList<>();

        for(Enrollment enrollment: enrollments){
            enrollmentResponses.add(enrollmentMapper.toResponse(enrollment));
        }
        return enrollmentResponses;
    }

    @Override
    public boolean isEnrolled(Long userId, Long courseId, String token) {
        UserResponse user = userClient.getUserId(userId,token);
        CourseResponse course = courseClient.getCourseById(courseId,token);

        return enrollmentRepository.existsByUserIdAndCourseId(userId, courseId);
    }
}
