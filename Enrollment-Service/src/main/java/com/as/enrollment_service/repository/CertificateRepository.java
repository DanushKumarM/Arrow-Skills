package com.as.enrollment_service.repository;

import com.as.enrollment_service.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
