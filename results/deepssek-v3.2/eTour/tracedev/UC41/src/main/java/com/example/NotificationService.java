package com.example;

/**
 * Interface for notification serv.
 */
public interface NotificationService {
    void sendSuccessNotification(String message);
    void sendErrorNotification(String message);
    void sendCancelNotification(String message); // Added to satisfy requirement Exit Conditions
}