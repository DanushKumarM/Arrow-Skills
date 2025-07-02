package com.as.course_service.controller;

import com.as.course_service.dto.LectureRequest;
import com.as.course_service.dto.LectureResponse;
import com.as.course_service.service.LectureService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public LectureResponse createLecture(@RequestBody LectureRequest request) {
        return lectureService.createLecture(request);
    }

    @GetMapping("/section/{sectionId}")
    public List<LectureResponse> getLecturesBySectionId(@PathVariable Long sectionId) {
        return lectureService.getLecturesBySectionId(sectionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LectureResponse> getLectureById(@PathVariable Long id){
        return ResponseEntity.ok(lectureService.getLectureById(id));
    }
}
