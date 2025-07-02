package com.as.enrollment_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Certificate  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long userId;
    private Long courseId;

    private String certificateUrl;

    private LocalDateTime issuedAt;

    public void onIssue() {
        this.issuedAt = LocalDateTime.now();
    }

}
