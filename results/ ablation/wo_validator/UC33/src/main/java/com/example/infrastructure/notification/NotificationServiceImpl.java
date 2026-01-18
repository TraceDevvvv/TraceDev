package com.example.infrastructure.notification;

import com.example.application.interfaces.INotificationService;

/**
 * Concrete notification service that logs to console.
 */
public class NotificationServiceImpl implements INotificationService {
    @Override
    public void notifySuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    @Override
    public void notifyError(String message) {
        System.out.println("[ERROR] " + message);
    }
}