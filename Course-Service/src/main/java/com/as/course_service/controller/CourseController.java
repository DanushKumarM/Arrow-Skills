package com.as.course_service.controller;

import com.as.course_service.dto.CourseRequest;
import com.as.course_service.dto.CourseResponse;
import com.as.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest request,
                                          @RequestHeader("Authorization")  String token){
        courseService.create(request, token);
        return ResponseEntity.ok("Success");
    }
    @GetMapping
    public List<CourseResponse> getAllCourses(@RequestHeader("Authorization")  String token){
        return courseService.getAllCourses(token);
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id,
                                  @RequestHeader("Authorization")  String token) {
        return courseService.getById(id, token);
    }
}
