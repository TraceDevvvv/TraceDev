package com.example.convention;

/**
 * Adapter interface for sending activation notifications.
 */
public interface NotificationAdapter {
    /**
     * Sends an activation notification for the given convention.
     * @param conventionId the ID of the activated convention.
     */
    void sendActivationNotification(String conventionId);
}