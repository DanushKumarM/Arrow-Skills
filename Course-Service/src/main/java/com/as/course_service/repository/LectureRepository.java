package com.as.course_service.repository;

import com.as.course_service.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findBySectionId(Long sectionId);
}
