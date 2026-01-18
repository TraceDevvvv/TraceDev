package com.example.bannerchecker;

/**
 * Notification service implementation that prints messages to the console.
 */
public class ConsoleNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("[NOTIFICATION] " + message);
    }
}