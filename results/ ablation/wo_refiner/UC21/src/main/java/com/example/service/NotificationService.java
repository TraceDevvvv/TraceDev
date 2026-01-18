package com.example.service;

/**
 * Service for sending notifications to the user.
 * Stereotype <<reliable>> - must maintain 99.9% availability and max response time < 2s.
 */
public class NotificationService {

    /**
     * Sends a success notification.
     */
    public void sendSuccess(String message) {
        System.out.println("[SUCCESS] " + message);
        // In a real application, this would update the UI or send a push notification.
    }

    /**
     * Sends an error notification.
     */
    public void sendError(String error) {
        System.out.println("[ERROR] " + error);
        // In a real application, this would update the UI or send a push notification.
    }
}