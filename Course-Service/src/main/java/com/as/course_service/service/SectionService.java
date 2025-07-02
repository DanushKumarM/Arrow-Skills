package com.as.course_service.service;

import com.as.course_service.dto.SectionRequest;
import com.as.course_service.dto.SectionResponse;

import java.util.List;

public interface SectionService {

    SectionResponse createSection(SectionRequest request);
    SectionResponse getSectionById(Long id);
    List<SectionResponse> getSectionsByCourseId(Long courseId);
}
