package com.example.banner.infrastructure.notification;

import com.example.banner.domain.port.NotificationPort;

/**
 * Adapter that sends notifications via email.
 */
public class EmailNotificationAdapter implements NotificationPort {
    // Assumption: EmailService is an external dependency.
    private Object emailService;

    public EmailNotificationAdapter(Object emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendConfirmation(String message) {
        // Simulate email sending.
        System.out.println("[EMAIL CONFIRMATION] " + message);
    }

    @Override
    public void notifyError(String error) {
        // Simulate error notification.
        System.out.println("[EMAIL ERROR] " + error);
    }
}