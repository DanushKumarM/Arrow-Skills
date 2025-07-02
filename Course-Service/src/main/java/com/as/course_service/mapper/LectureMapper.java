package com.as.course_service.mapper;

import com.as.course_service.dto.LectureRequest;
import com.as.course_service.dto.LectureResponse;
import com.as.course_service.model.Lecture;
import com.as.course_service.model.Section;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {

    public Lecture toEntity(LectureRequest request, Section section){
        return Lecture.builder()
                .title(request.getTitle())
                .url(request.getUrl())
                .section(section)
                .build();
    }
    public LectureResponse toResponse(Lecture lecture){
        return LectureResponse.builder()
                .id(lecture.getId())
                .title(lecture.getTitle())
                .url(lecture.getUrl())
                .courseId(lecture.getSection().getCourse().getId())
                .build();
    }
}
