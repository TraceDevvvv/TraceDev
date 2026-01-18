package com.example.service;

/**
 * Notification service to send success and error notifications.
 */
public class NotificationService {
    public NotificationService() {
    }

    // Missing method from class diagram: sendSuccessNotification
    public void sendSuccessNotification(String userId, String message) {
        System.out.println("NotificationService: Success notification sent to user " + userId + " - " + message);
    }

    // Missing method from class diagram: sendErrorNotification
    public void sendErrorNotification(String userId, String error) {
        System.out.println("NotificationService: Error notification sent to user " + userId + " - " + error);
    }
}