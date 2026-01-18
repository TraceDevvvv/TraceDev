package com.example;

/**
 * Port interface defining the contract for sending notifications.
 * This abstracts the notification mechanism.
 */
public interface NotificationServicePort {

    /**
     * Sends a notification to a specified recipient.
     *
     * @param recipientEmail The email address of the recipient.
     * @param message        The message content of the notification.
     * @throws NotificationFailedException if the notification sending process fails.
     */
    void sendNotification(String recipientEmail, String message) throws NotificationFailedException;
}