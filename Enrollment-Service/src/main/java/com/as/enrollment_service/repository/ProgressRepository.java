package com.as.enrollment_service.repository;

import com.as.enrollment_service.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Progress findByUserIdAndLectureId(Long userId, Long userId1);
    List<Progress> findByUserIdAndCourseId(Long userId, Long courseId);

}
