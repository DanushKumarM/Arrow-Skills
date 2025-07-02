package com.as.enrollment_service.controller;

import com.as.enrollment_service.dto.EnrollmentResponse;
import com.as.enrollment_service.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponse> enroll(@RequestParam Long userId,
                                           @RequestParam Long courseId,
                                           @RequestHeader("Authorization") String token){
        EnrollmentResponse response = enrollmentService.enroll(userId,courseId,token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<EnrollmentResponse>> getEnrolledByUser(@PathVariable Long userId,
                                           @RequestHeader("Authorization") String token){
        List<EnrollmentResponse> response = enrollmentService.getEnrollmentByUser(userId,token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/exists/{userId}/{courseId}")
    public  ResponseEntity<Boolean> isEnrolled (@PathVariable Long userId,
                                                @PathVariable Long courseId,
                                                @RequestHeader("Authorization") String token){
        boolean exists = enrollmentService.isEnrolled(userId,courseId,token);
        return ResponseEntity.ok(exists);
    }

}
