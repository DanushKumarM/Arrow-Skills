package com.as.enrollment_service.service;

import com.as.enrollment_service.client.CourseClient;
import com.as.enrollment_service.client.UserClient;
import com.as.enrollment_service.dto.CertificateResponse;
import com.as.enrollment_service.dto.CourseResponse;
import com.as.enrollment_service.dto.SectionResponse;
import com.as.enrollment_service.dto.UserResponse;
import com.as.enrollment_service.exception.ResourceAlreadyExistsException;
import com.as.enrollment_service.mapper.CertificateMapper;
import com.as.enrollment_service.model.Certificate;
import com.as.enrollment_service.model.Progress;
import com.as.enrollment_service.repository.CertificateRepository;
import com.as.enrollment_service.repository.ProgressRepository;
import com.as.enrollment_service.util.CertificatePdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;
    private  final ProgressRepository progressRepository;
    private  final CourseClient courseClient;
    private final UserClient userClient;
    private final CertificatePdfGenerator certificatePdfGenerator;


    @Override
    public CertificateResponse generateCertificate(Long userId, Long courseId, String token) {

        if (certificateRepository.findByUserIdAndCourseId(userId, courseId).isPresent()) {
            throw new RuntimeException("Certificate already issued.");
        }

       CourseResponse course = courseClient.getCourseById(courseId,token);

        int totalLectures = 0;
        for(SectionResponse response:course.getSections()){
            if(response.getLectures() != null){
                totalLectures += response.getLectures().size();
            }
        }

        long completedCount = 0;
        List<Progress> processes = progressRepository.findByUserIdAndCourseId(userId,courseId);
        for(Progress progress: processes){
            if(progress.isCompleted()){completedCount++;}
        }
        if (completedCount < totalLectures) {
            throw new RuntimeException("All lectures not completed.");
        }
        try {
            UserResponse user = userClient.getUserId(userId, token);
            String studentName = user.getName();

            String courseTitle = course.getTitle();
            String date = LocalDate.now().toString();
            String certId = UUID.randomUUID().toString();


            byte[] pdfBytes = certificatePdfGenerator.generate(studentName, courseTitle, date, certId);

            Certificate cert = Certificate.builder()
                    .userId(userId)
                    .courseId(courseId)
                    .issuedAt(LocalDateTime.now())
                    .certificateUrl("/api/certificates/download/" + certId)
                    .pdf(pdfBytes)
                    .build();

            return certificateMapper.toResponse(certificateRepository.save(cert));

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate certificate: " + e.getMessage());
        }
    }

    @Override
    public CertificateResponse getCertificate(Long userId, Long courseId) {
        Certificate cert = certificateRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new ResourceAlreadyExistsException("Certificate already issued"));
        return certificateMapper.toResponse(cert);
    }
}
