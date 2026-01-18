package com.example.infrastructure;

import com.example.application.INotificationService;

/**
 * Infrastructure Layer - Concrete notification (Observer/Event).
 * Marked as reliable per diagram.
 */
public class EmailNotificationService implements INotificationService {
    // SmtpClient simulation
    private Object smtpClient;

    public EmailNotificationService() {
        this.smtpClient = new Object(); // placeholder
    }

    @Override
    public void notifyAccountDeleted(int touristId) {
        System.out.println("EmailNotificationService: Sending account deletion notification for tourist ID " + touristId);
        // Actual SMTP logic would go here.
    }
}