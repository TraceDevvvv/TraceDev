package com.example.service;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    /**
     * Notifies that deletion was successful.
     */
    public void notifyDeletionSuccess() {
        System.out.println("Notification: Tags deleted successfully.");
        // In a real application, this could send an email, log, or push notification.
    }
}