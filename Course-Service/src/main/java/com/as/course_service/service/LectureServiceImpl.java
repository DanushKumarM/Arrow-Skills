package com.as.course_service.service;

import com.as.course_service.dto.LectureRequest;
import com.as.course_service.dto.LectureResponse;
import com.as.course_service.mapper.LectureMapper;
import com.as.course_service.model.Lecture;
import com.as.course_service.model.Section;
import com.as.course_service.repository.LectureRepository;
import com.as.course_service.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements  LectureService{

    private final LectureRepository lectureRepository;
    private final SectionRepository sectionRepository;
    private final LectureMapper lectureMapper;

    @Override
    public LectureResponse createLecture(LectureRequest request) {
        Section section = sectionRepository.findById(request.getSectionId()).orElseThrow(()->
                new RuntimeException("Section Id not found"));
        Lecture lecture = lectureMapper.toEntity(request, section);
        Lecture savedLecture = lectureRepository.save(lecture);

        return lectureMapper.toResponse(savedLecture);
    }

    @Override
    public LectureResponse getLectureById(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Lecture Id not found"));
        return lectureMapper.toResponse(lecture);
    }

    @Override
    public List<LectureResponse> getLecturesBySectionId(Long sectionId) {
        List<Lecture> lectures = lectureRepository.findBySectionId(sectionId);
        List<LectureResponse> lectureResponseList = new ArrayList<>();

        for(Lecture lecture: lectures){
            LectureResponse response = lectureMapper.toResponse(lecture);
            lectureResponseList.add(response);
        }
        return  lectureResponseList;
    }
}
