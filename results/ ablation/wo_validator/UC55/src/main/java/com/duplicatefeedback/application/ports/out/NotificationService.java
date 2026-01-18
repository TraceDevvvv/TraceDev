package com.duplicatefeedback.application.ports.out;

/**
 * Output port for notification delivery.
 * Part of the interface adapters layer.
 */
public interface NotificationService {
    boolean sendNotification(String userId, String message);
}