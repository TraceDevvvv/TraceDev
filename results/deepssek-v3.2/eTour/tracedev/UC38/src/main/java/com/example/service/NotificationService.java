package com.example.service;

/**
 * Handles sending notifications to users.
 */
public class NotificationService {
    public boolean sendNotification(String message, String userId) {
        System.out.println("Notification to user " + userId + ": " + message);
        return true;
    }

    public void notifySuccess(String operatorId) {
        sendNotification("Banner insertion successful", operatorId);
    }

    public void notifyFailure(String operatorId, String error) {
        sendNotification("Banner insertion failed: " + error, operatorId);
    }
}