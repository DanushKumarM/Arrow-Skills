package com.as.course_service.service;

import com.as.course_service.dto.CourseRequest;
import com.as.course_service.dto.CourseResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

     CourseResponse create(CourseRequest request, String token);

    List<CourseResponse> getAllCourses(String token);

    CourseResponse getById(Long id,String token);




}
