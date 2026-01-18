package com.example;

/**
 * Service responsible for sending notifications.
 * Interacts with ETOURServerAdapter to notify about banner changes.
 */
public class NotificationService {
    private ETOURServerAdapter etourServerAdapter;

    public NotificationService(ETOURServerAdapter etourServerAdapter) {
        this.etourServerAdapter = etourServerAdapter;
    }

    public void sendSuccessNotification(String message) {
        System.out.println("Success Notification: " + message);
        // In a real implementation, this might log or send a message to a user interface.
    }

    public void sendServerConnectionError(String errorMessage) {
        System.out.println("Server Connection Error: " + errorMessage);
        // As per sequence diagram, this method also notifies ETOURServerAdapter.
        etourServerAdapter.notifyBannerChanged(0); // bannerId unknown, using placeholder
    }
}