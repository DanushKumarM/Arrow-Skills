package com.as.course_service.service;

import com.as.course_service.dto.SectionRequest;
import com.as.course_service.dto.SectionResponse;
import com.as.course_service.mapper.SectionMapper;
import com.as.course_service.model.Course;
import com.as.course_service.model.Section;
import com.as.course_service.repository.CourseRepository;
import com.as.course_service.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService{

    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final CourseRepository courseRepository;


    @Override
    public SectionResponse createSection(SectionRequest request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(()->
                new RuntimeException("Course Id not found"));
        Section section = sectionMapper.toEntity(request, course);
        Section savedSection = sectionRepository.save(section);
        return sectionMapper.toResponse(savedSection);
    }

    @Override
    public SectionResponse getSectionById(Long id) {
        Section section = sectionRepository.findById(id).orElseThrow(()->
                new RuntimeException("Section Id not found"));
        return sectionMapper.toResponse(section);
    }

    @Override
    public List<SectionResponse> getSectionsByCourseId(Long courseId) {
        List<Section> sections = sectionRepository.findByCourseId(courseId);
        List<SectionResponse> sectionResponseList = new ArrayList<>();

        for(Section section: sections){
            SectionResponse response = sectionMapper.toResponse(section);
            sectionResponseList.add(response);
        }
        return sectionResponseList;
    }
}
