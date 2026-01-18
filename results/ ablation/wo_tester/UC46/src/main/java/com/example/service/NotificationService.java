package com.example.service;

/**
 * Utility service for sending notifications to tourists.
 */
public class NotificationService {
    // Sends a notification to the specified tourist.
    public void sendNotification(String touristId, String message) {
        // In a real system, this might send an email, push notification, etc.
        System.out.println("Notification to tourist " + touristId + ": " + message);
    }
}