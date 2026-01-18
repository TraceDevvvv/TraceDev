package com.example.bookmarking.infrastructure;

/**
 * Service for sending notifications to users or other systems.
 */
public class NotificationService {

    /**
     * Sends a notification message.
     * In a real system, this could involve sending emails, push notifications,
     * or logging messages for user feedback.
     *
     * @param message The message content of the notification.
     */
    public void sendNotification(String message) {
        System.out.println("NotificationService: Sending notification -> \"" + message + "\"");
        // In a real application, this would integrate with a notification provider
        // e.g., email service, push notification service, etc.
    }
}