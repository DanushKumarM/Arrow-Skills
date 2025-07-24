package com.as.notification_service.service;

import com.as.notification_service.dto.CourseEventDTO;
import com.as.notification_service.dto.EnrollmentEventDTO;
import com.as.notification_service.dto.NotificationEvent;
import com.as.notification_service.model.Notification;
import com.as.notification_service.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final EmailSenderService emailSenderService;
    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getUserId())
                .toEmail(event.getToEmail())
                .subject(event.getSubject())
                .message(event.getMessage())
                .build();

        try {
            emailSenderService.sendEmail(
                    event.getToEmail(),
                    event.getSubject(),
                    event.getMessage()
            );
            notification.setSuccess(true);
        } catch (Exception e) {
            notification.setSuccess(false);
            notification.setErrorMessage(e.getMessage());
        }

        notificationRepository.save(notification);
    }


    @Override
    public void sendCourseNotification(CourseEventDTO courseEventDTO) {
        Notification notification = Notification.builder()
                .userId(courseEventDTO.getCourseId())
                .toEmail(courseEventDTO.getInstructorEmail())
                .subject("New Course Created: " + courseEventDTO.getTitle())
                .message("Your course \"" + courseEventDTO.getTitle() + "\" has been successfully created.")
                .build();

        try {
            emailSenderService.sendEmail(
                    courseEventDTO.getInstructorEmail(),
                    "New Course Created: " + courseEventDTO.getTitle(),
                    notification.getMessage()
            );
            notification.setSuccess(true);
        } catch (Exception e){
            notification.setSuccess(false);
            notification.setErrorMessage(e.getMessage());
        }
        notificationRepository.save(notification);
    }

    @Override
    public void sendEnrollmentNotification(EnrollmentEventDTO enrollmentEventDTO) {
        Notification notification = Notification.builder()
                .userId(enrollmentEventDTO.getCourseId())
                .toEmail(enrollmentEventDTO.getStudentEmail())
                .subject("Enrollment Successful: " + enrollmentEventDTO.getCourseTitle())
                .message("Hi " + enrollmentEventDTO.getStudentName() + ",\n\n"
                        + "You have successfully enrolled in \"" + enrollmentEventDTO.getCourseTitle() + "\".\n"
                        + "Start learning today!\n\n- ArrowSkills Team")
                .build();

        try {
            emailSenderService.sendEmail(
                    enrollmentEventDTO.getStudentEmail(),
                    "Enrollment Successful: " + enrollmentEventDTO.getCourseTitle(),
                    notification.getMessage()
            );
            notification.setSuccess(true);
        }catch (Exception e){
            notification.setSuccess(false);
            notification.setErrorMessage(e.getMessage());
        }
        notificationRepository.save(notification);
    }
}
