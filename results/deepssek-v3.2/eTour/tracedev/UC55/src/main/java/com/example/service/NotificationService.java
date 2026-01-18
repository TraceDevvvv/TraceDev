package com.example.service;

/**
 * Interface for sending notifications.
 */
public interface NotificationService {
    /**
     * Sends a notification to a user.
     * @param userId the user identifier.
     * @param message the notification message.
     */
    void sendNotification(String userId, String message);

    /**
     * Notification sent.
     * Corresponds to sequence diagram message m10.
     */
    default void notificationSent(String userId, String message) {
        sendNotification(userId, message);
    }
}