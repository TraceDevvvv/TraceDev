package com.example.notification;

import java.util.UUID;

/**
 * Notification service that sends emails (simulated).
 */
public class EmailNotificationService implements NotificationService {
    
    public EmailNotificationService() {
        // Constructor
    }
    
    @Override
    public void notifySuccess(String message) {
        System.out.println("[Email Notification - Success] " + message);
    }
    
    @Override
    public void notifyError(String message) {
        System.out.println("[Email Notification - Error] " + message);
    }
    
    @Override
    public void notifyInclusion(UUID culturalGoodId) {
        System.out.println("[Email Notification - Inclusion] Cultural good with ID " + culturalGoodId + " has been successfully included.");
    }
}