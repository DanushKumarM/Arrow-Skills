package com.as.enrollment_service.service;

import com.as.enrollment_service.dto.CertificateResponse;
import org.springframework.stereotype.Service;

public interface CertificateService {

    CertificateResponse generateCertificate(Long userId, Long courseId, String token);
    CertificateResponse getCertificate(Long userId, Long courseId);
}
