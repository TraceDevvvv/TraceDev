package com.example.notification;

/**
 * Interface for sending notifications.
 */
public interface NotificationService {
    
    /**
     * Sends a success notification.
     */
    void sendSuccessNotification();
    
    /**
     * Sends an error notification.
     */
    void sendErrorNotification();
}