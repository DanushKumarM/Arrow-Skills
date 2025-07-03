package com.as.enrollment_service.repository;

import com.as.enrollment_service.model.CertificateAssets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificateAssetsRepository extends JpaRepository<CertificateAssets, Long> {
    Optional<CertificateAssets> findByName(String name);
}

