package com.example;

/**
 * Port interface for displaying notifications to the user or system.
 * Annotated with <<Port>> stereotype.
 */
public interface NotificationService {

    /**
     * Displays a notification message.
     *
     * @param message The message to be displayed.
     */
    void displayNotification(String message);
}