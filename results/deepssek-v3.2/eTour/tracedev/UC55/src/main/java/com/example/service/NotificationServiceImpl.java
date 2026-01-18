package com.example.service;

/**
 * Implementation of NotificationService using a messaging gateway.
 */
public class NotificationServiceImpl implements NotificationService {
    // In a real implementation, this would be injected.
    private Object messagingGateway;

    public NotificationServiceImpl(Object messagingGateway) {
        this.messagingGateway = messagingGateway;
    }

    @Override
    public void sendNotification(String userId, String message) {
        // Simulate sending notification.
        System.out.println("Sending notification to user " + userId + ": " + message);
    }

    @Override
    public void notificationSent(String userId, String message) {
        sendNotification(userId, message);
        System.out.println("Notification sent: " + message);
    }
}