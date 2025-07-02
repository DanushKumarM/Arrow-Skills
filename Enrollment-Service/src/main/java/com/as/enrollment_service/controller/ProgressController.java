package com.as.enrollment_service.controller;

import com.as.enrollment_service.dto.ProgressResponse;
import com.as.enrollment_service.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/progress")
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/mark/{userId}/{lectureId}")
    public ResponseEntity<ProgressResponse> markProgress(
            @PathVariable Long userId,
            @PathVariable Long lectureId,
            @RequestHeader("Authorization") String token){

        ProgressResponse response = progressService.markProgress(userId,lectureId,token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<List<ProgressResponse>> getProgressByUserAndCourse(
            @PathVariable Long userId,
            @PathVariable Long courseId,
            @RequestHeader("Authorization") String token) {

        List<ProgressResponse> responses = progressService.getProgressByUserAndCourse(userId, courseId, token);
        return ResponseEntity.ok(responses);
    }


}
