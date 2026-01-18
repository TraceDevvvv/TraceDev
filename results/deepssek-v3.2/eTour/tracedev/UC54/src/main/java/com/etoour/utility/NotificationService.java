package com.etoour.utility;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    public void sendNotification(String userId, String message) {
        System.out.println("Notification sent to user " + userId + ": " + message);
    }
}