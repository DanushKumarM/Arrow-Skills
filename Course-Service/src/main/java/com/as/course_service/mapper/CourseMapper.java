package com.as.course_service.mapper;

import com.as.course_service.dto.CourseRequest;
import com.as.course_service.dto.CourseResponse;
import com.as.course_service.model.Category;
import com.as.course_service.model.Course;
import com.as.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequest request, Category category) {
        return Course.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .courseType(request.getCourseType())
                .thumbnailUrl(request.getThumbnailUrl())
                .contentUrl(request.getContentUrl())
                .price(request.getPrice())
                .category(category)
                .instructorId(request.getInstructorId())
                .createdAt(java.time.LocalDateTime.now())
                .build();
    }
    public CourseResponse toResponse(Course course, String instructorName) {
         return   CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .courseType(course.getCourseType())
                .thumbnailUrl(course.getThumbnailUrl())
                .contentUrl(course.getContentUrl())
                .price(course.getPrice())
                .categoryName(course.getCategory().getName())
                .instructorId(course.getInstructorId())
                .instructorName(instructorName)
                .createdAt(course.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();
    }
    }



