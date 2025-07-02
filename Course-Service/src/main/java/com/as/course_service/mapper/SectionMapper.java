package com.as.course_service.mapper;

import com.as.course_service.dto.LectureResponse;
import com.as.course_service.dto.SectionRequest;
import com.as.course_service.dto.SectionResponse;
import com.as.course_service.model.Course;
import com.as.course_service.model.Lecture;
import com.as.course_service.model.Section;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SectionMapper {

    private final LectureMapper lectureMapper;

    public Section toEntity(SectionRequest request, Course course){
        return Section.builder()
                .title(request.getTitle())
                .course(course)
                .build();
    }

    public SectionResponse toResponse(Section section){
        List<LectureResponse> lectureResponses = new ArrayList<>();
        if (section.getLectures() != null) {
            for (Lecture lecture : section.getLectures()) {
                LectureResponse response = lectureMapper.toResponse(lecture);
                lectureResponses.add(response);
            }
        }
        return SectionResponse.builder()
                .id(section.getId())
                .title(section.getTitle())
                .lectures(lectureResponses)
                .build();
    }
}
