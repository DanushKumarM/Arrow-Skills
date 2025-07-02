package com.as.course_service.model;

import com.as.course_service.enums.CourseType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private CourseType courseType;

    private String thumbnailUrl;
    private String contentUrl;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    private Category category;

    private long instructorId;

    private LocalDateTime createdAt;


}

