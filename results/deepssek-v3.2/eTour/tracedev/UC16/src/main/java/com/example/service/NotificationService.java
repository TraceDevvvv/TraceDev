package com.example.service;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    public void sendDeletionNotification(String touristId) {
        // Quality Requirement: Notify about deletion
        System.out.println("Notification: Tourist with ID " + touristId + " has been deleted.");
        // In a real application, this might send an email, log, or push notification.
    }
}