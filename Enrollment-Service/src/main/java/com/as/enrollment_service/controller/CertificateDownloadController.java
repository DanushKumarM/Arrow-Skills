package com.as.enrollment_service.controller;

import com.as.enrollment_service.model.Certificate;
import com.as.enrollment_service.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
@RequiredArgsConstructor
public class CertificateDownloadController {

    private final CertificateRepository certificateRepository;

    @GetMapping("/download/{certId}")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable String certId) {

        List<Certificate> certificates = certificateRepository.findAll();
        Certificate foundCert = null;

        for (Certificate cert : certificates) {
            if (cert.getCertificateUrl() != null && cert.getCertificateUrl().endsWith(certId)) {
                foundCert = cert;
                break;
            }
        }

        if (foundCert == null) {
            throw new RuntimeException("Certificate not found");
        }

        byte[] pdfData = foundCert.getPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
                .builder("attachment")
                .filename("certificate-" + certId + ".pdf")
                .build());

        return new ResponseEntity<byte[]>(pdfData, headers, HttpStatus.OK);
    }
}

