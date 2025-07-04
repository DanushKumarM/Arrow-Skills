package com.as.review_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long courseId;

    @Column(length = 1000)
    private String comment;

    private Integer rating; // 1 to 5

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreated(){
        this.createdAt = LocalDateTime.now();
    }

}
