package com.as.course_service.controller;

import com.as.course_service.dto.SectionRequest;
import com.as.course_service.dto.SectionResponse;
import com.as.course_service.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public SectionResponse createSection(@RequestBody SectionRequest request) {
        return sectionService.createSection(request);
    }

    @GetMapping("/{id}")
    public SectionResponse getSectionById(@PathVariable Long id) {
        return sectionService.getSectionById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<SectionResponse> getSectionsByCourseId(@PathVariable Long courseId) {
        return sectionService.getSectionsByCourseId(courseId);
    }
}
