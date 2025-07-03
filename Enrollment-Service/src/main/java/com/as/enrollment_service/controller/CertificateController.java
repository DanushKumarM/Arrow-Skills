package com.as.enrollment_service.controller;

import com.as.enrollment_service.dto.CertificateResponse;
import com.as.enrollment_service.service.CertificateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certificate")
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping
    public ResponseEntity<CertificateResponse> generateCertificate(@RequestParam Long userId,
                                                                   @RequestParam Long courseId,
                                                                   @RequestHeader("Authorization") String token){
        CertificateResponse certificateResponse = certificateService.generateCertificate(userId,courseId,token);
        return ResponseEntity.ok(certificateResponse);
    }

    @GetMapping("/{userId}/{courseId}")
    public ResponseEntity<CertificateResponse> getCertificate(@PathVariable Long userId,
                                                              @PathVariable Long courseId){
        CertificateResponse certificateResponse = certificateService.getCertificate(userId,courseId);
        return  ResponseEntity.ok(certificateResponse);
    }
}

