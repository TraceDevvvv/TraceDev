package com.example.service;

/**
 * Implementation of NotificationService.
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(String message) {
        // Simulate sending a notification
        System.out.println("[NOTIFICATION] " + message);
    }
}