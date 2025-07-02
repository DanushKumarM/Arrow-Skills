package com.as.course_service.repository;

import com.as.course_service.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByCourseId(Long courseId);
}
