package com.example.service;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    /**
     * Sends a success notification to the operator.
     */
    public void sendSuccessNotification(String operatorId, String message) {
        System.out.println("Notification to operator " + operatorId + ": " + message);
        // In real scenario, could be email, SMS, or in-app notification
    }
}