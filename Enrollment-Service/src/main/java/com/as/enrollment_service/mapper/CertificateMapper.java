package com.as.enrollment_service.mapper;

import com.as.enrollment_service.dto.CertificateResponse;
import com.as.enrollment_service.model.Certificate;
import org.springframework.stereotype.Component;

@Component
public class CertificateMapper {

    public CertificateResponse toResponse (Certificate certificate){
        return CertificateResponse.builder()
                .id(certificate.getId())
                .userId(certificate.getUserId())
                .courseId(certificate.getCourseId())
                .certificateUrl(certificate.getCertificateUrl())
                .issuedAt(certificate.getIssuedAt())
                .build();
    }
}
