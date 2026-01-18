package com.example.exception;

/**
 * Service for user notifications and confirmations.
 */
public class NotificationService {

    /**
     * Displays an error message to the user.
     * @param message The error message.
     */
    public void showErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    /**
     * Requests confirmation from the user.
     * @param message The confirmation prompt.
     * @return true if user confirms, false otherwise.
     */
    public boolean requestConfirmation(String message) {
        System.out.println("[CONFIRM] " + message);
        // Simulate user confirmation: assume true for this flow.
        return true;
    }

    /**
     * Confirms that the user has read the notification.
     */
    public void confirmNotificationRead() {
        System.out.println("Notification read confirmed.");
    }
}