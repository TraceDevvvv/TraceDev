package com.duplicatefeedback.application.strategy;

/**
 * Strategy interface for user notification.
 * Part of the application business rules (Use Case Layer).
 */
public interface UserNotificationStrategy {
    /**
     * Notify the user with a message.
     * @param message the message to send
     */
    void notifyUser(String message);

    /**
     * Abstract method to deliver notification to a specific user.
     * @param userId the user identifier
     * @param message the message to deliver
     */
    void deliverNotification(String userId, String message);
}