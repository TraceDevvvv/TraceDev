package com.example.banner.domain.port;

/**
 * Port for sending notifications (confirmation/error).
 */
public interface NotificationPort {
    void sendConfirmation(String message);
    void notifyError(String error);
}