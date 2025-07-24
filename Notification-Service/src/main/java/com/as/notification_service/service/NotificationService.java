package com.as.notification_service.service;

import com.as.notification_service.dto.CourseEventDTO;
import com.as.notification_service.dto.EnrollmentEventDTO;
import com.as.notification_service.dto.NotificationEvent;

public interface NotificationService {

    public void sendNotification(NotificationEvent event);

    public void sendCourseNotification(CourseEventDTO courseEventDTO);

    public void sendEnrollmentNotification(EnrollmentEventDTO enrollmentEventDTO);

}
