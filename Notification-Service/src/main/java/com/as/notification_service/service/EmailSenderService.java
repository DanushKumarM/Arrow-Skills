package com.as.notification_service.service;

public interface EmailSenderService {

    public void sendEmail(String to, String subject, String body);
}
