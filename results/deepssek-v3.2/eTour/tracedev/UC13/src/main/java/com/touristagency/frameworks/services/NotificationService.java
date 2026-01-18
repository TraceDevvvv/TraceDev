package com.touristagency.frameworks.serv;

/**
 * Service for sending notifications (e.g., email, SMS, in-app).
 */
public class NotificationService {
    /**
     * Sends a notification with the given message.
     * @param message the notification content
     */
    public void sendNotification(String message) {
        // In a real system, this would integrate with email, SMS, or push notification serv
        System.out.println("[Notification] " + message);
    }
}