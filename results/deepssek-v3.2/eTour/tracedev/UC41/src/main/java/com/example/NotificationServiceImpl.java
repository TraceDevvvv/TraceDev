package com.example;

/**
 * Implementation of NotificationService.
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendSuccessNotification(String message) {
        System.out.println("SUCCESS: " + message);
    }

    @Override
    public void sendErrorNotification(String message) {
        System.out.println("ERROR: " + message);
    }

    @Override
    public void sendCancelNotification(String message) {
        System.out.println("CANCELLED: " + message);
    }
}