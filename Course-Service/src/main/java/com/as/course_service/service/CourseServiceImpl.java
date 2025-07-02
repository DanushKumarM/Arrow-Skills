package com.as.course_service.service;

import com.as.course_service.client.InstructorClient;
import com.as.course_service.dto.CourseRequest;
import com.as.course_service.dto.CourseResponse;
import com.as.course_service.mapper.CourseMapper;
import com.as.course_service.model.Category;
import com.as.course_service.model.Course;
import com.as.course_service.repository.CategoryRepository;
import com.as.course_service.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements  CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CategoryRepository categoryRepository;
    private final InstructorClient instructorClient;


    @Override
    public CourseResponse create(CourseRequest request, String token) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(()-> new RuntimeException("Category Not found"));


        Course course = courseMapper.toEntity(request,category);
        String instructorName = instructorClient.getInstructorName(course.getInstructorId(), token);
        Course savedCourse = courseRepository.save(course);

        return  courseMapper.toResponse(savedCourse, instructorName);
    }

    @Override
    public List<CourseResponse> getAllCourses(String token) {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponse> responseList = new ArrayList<>();

        for(Course course: courses){
            String instructorName = instructorClient.getInstructorName(course.getInstructorId(),token);
            CourseResponse response = courseMapper.toResponse(course, instructorName);
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public CourseResponse getById(Long id,String token) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Course Id not found"));
        String instructorName = instructorClient.getInstructorName(course.getInstructorId(), token);
        return courseMapper.toResponse(course, instructorName);
    }
}
