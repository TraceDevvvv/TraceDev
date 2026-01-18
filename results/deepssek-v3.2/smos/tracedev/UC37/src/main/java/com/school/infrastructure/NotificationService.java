package com.school.infrastructure;

/**
 * Service interface for sending notifications.
 */
public interface NotificationService {
    void sendEmail(String to, String subject, String body);
}