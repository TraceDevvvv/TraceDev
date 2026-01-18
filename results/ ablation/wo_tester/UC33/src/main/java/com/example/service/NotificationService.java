package com.example.service;

/**
 * Interface for notification service.
 */
public interface NotificationService {
    /**
     * Sends a notification.
     * @param message the notification message
     */
    void sendNotification(String message);
}