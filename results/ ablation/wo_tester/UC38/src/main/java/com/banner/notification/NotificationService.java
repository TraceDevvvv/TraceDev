package com.banner.notification;

/**
 * Service for sending notifications about banner insertion (exit condition).
 */
public class NotificationService {
    public NotificationService() {}

    /**
     * Send notification about successful banner insertion.
     */
    public void sendInsertionNotification(String bannerId) {
        System.out.println("Notification sent for banner with ID: " + bannerId);
        // In real implementation would send email, push notification, etc.
    }
}