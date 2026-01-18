package com.example;

/**
 * Implementation of NotificationService.
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Notification: " + message);
        // In a real system, this might send an email, SMS, or push notification
    }
}