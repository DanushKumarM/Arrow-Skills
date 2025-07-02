package com.as.course_service.service;

import com.as.course_service.dto.LectureRequest;
import com.as.course_service.dto.LectureResponse;
import com.as.course_service.model.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LectureService {

    LectureResponse createLecture(LectureRequest request);

    LectureResponse getLectureById(Long id);

    List<LectureResponse> getLecturesBySectionId(Long sectionId);
}
