package com.example.notification;

/**
 * Concrete implementation of NotificationService.
 * Stereotype <<Performance>> as per requirement REQ-016.
 */
public class NotificationServiceImpl implements NotificationService {
    
    @Override
    public void sendSuccessNotification() {
        // In a real system, this might send an email, SMS, or UI notification.
        System.out.println("[Notification] Banner modification succeeded.");
    }
    
    @Override
    public void sendErrorNotification() {
        System.out.println("[Notification] Banner modification failed.");
    }
}