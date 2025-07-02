package com.as.course_service.model;

import com.as.course_service.enums.LectureType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url; // can be video or document

    @Enumerated(EnumType.STRING)
    private LectureType type; // VIDEO or DOCUMENT

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;
}
