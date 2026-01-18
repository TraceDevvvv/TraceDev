package com.example.service;

/**
 * Service for sending notifications and requesting confirmations.
 * Implements quality requirement: Clearly notifies user and provides clear path to return.
 */
public class NotificationService {
    /**
     * Sends an error notification to the user.
     * @param message the error message
     * @param userId the user identifier
     */
    public void sendErrorNotification(String message, String userId) {
        // In a real system, this might send an email, push notification, or log the error.
        System.out.println("Error notification sent to user " + userId + ": " + message);
    }

    /**
     * Requests a confirmation from the user.
     * For this scenario, always returns true (confirmed) as per sequence diagram.
     * @param message the confirmation message
     * @param userId the user identifier
     * @return true if confirmed, false otherwise
     */
    public boolean requestConfirmation(String message, String userId) {
        // In a real system, this might show a dialog and await user input.
        System.out.println("Confirmation requested from user " + userId + ": " + message);
        return true; // As per sequence diagram, returns true (confirmed)
    }

    /**
     * Returns notification sent as per sequence diagram message m6.
     * This method explicitly implements the return message from NotifySvc to Interactor.
     */
    public void notificationSent() {
        System.out.println("Notification sent return signal.");
    }

    /**
     * Returns true (confirmed) as per sequence diagram message m8.
     * This method explicitly implements the return message from NotifySvc to Interactor.
     * @return true
     */
    public boolean returnTrueConfirmed() {
        return true;
    }
}