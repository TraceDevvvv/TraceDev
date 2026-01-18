package com.example.service;

/**
 * External service for sending notifications.
 */
public class ETourService {
    private boolean connected = true; // Simulating connection state

    public boolean sendNotification(String message) {
        // Simulate sending a notification.
        System.out.println("ETourService: Sending notification: " + message);
        return true; // assume success for simplicity
    }

    public boolean isConnected() {
        return connected;
    }

    public boolean notifyConventionActivation(String conventionId) {
        if (!isConnected()) {
            return false;
        }
        String message = "Convention " + conventionId + " has been activated.";
        return sendNotification(message);
    }

    // For testing: simulate connection state
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}