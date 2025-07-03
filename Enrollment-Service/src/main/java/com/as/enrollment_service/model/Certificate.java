package com.as.enrollment_service.model;

import jakarta.persistence.*;
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

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] pdf;

    public void onIssue() {
        this.issuedAt = LocalDateTime.now();
    }

}
