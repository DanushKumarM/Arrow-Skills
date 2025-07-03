package com.as.enrollment_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CertificateAssets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // logo, background, signature

    @Lob
    private byte[] image;
}

