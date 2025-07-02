package com.as.enrollment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CertificateResponse {

    private long id;

    private Long userId;
    private Long courseId;

    private String certificateUrl;

    private LocalDateTime issuedAt;
}
