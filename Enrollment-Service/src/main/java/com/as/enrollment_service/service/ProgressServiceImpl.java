package com.as.enrollment_service.service;

import com.as.enrollment_service.client.CourseClient;
import com.as.enrollment_service.client.LectureClient;
import com.as.enrollment_service.client.UserClient;
import com.as.enrollment_service.dto.*;
import com.as.enrollment_service.exception.ResourceNotFoundException;
import com.as.enrollment_service.mapper.ProgressMapper;
import com.as.enrollment_service.model.Enrollment;
import com.as.enrollment_service.model.Progress;
import com.as.enrollment_service.repository.EnrollmentRepository;
import com.as.enrollment_service.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService{

    private final ProgressRepository progressRepository;
    private final ProgressMapper progressMapper;
    private  final UserClient userClient;
    private final EnrollmentRepository enrollmentRepository;
    private final LectureClient lectureClient;



    @Override
    public ProgressResponse markProgress(Long userId, Long lectureId, String token) {

        UserResponse user = userClient.getUserId(userId, token);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        LectureResponse lecture = lectureClient.getLectureById(lectureId);
        if (lecture == null) {
            throw new ResourceNotFoundException("Lecture not found");
        }
        Long courseId = lecture.getCourseId();

        Enrollment enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not enrolled in this course"));

        Progress progress = new Progress();
        progress.setUserId(userId);
        progress.setCourseId(courseId);
        progress.setLectureId(lectureId);
        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());

        Progress savedProgress = progressRepository.save(progress);
        return progressMapper.toResponse(savedProgress);
    }

    @Override
    public List<ProgressResponse> getProgressByUserAndCourse(Long userId, Long courseId, String token) {

        List<Progress>  progresses = progressRepository.findByUserIdAndCourseId(userId,courseId);
        List<ProgressResponse> responseList = new ArrayList<>();

        for(Progress progress: progresses){
            ProgressResponse response = progressMapper.toResponse(progress);
            responseList.add(response);
        }
        return responseList;
    }
}
