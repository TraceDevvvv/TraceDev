package com.example.newsapp.infrastructure.service;

/**
 * Provides system-wide notification capabilities.
 * Supports REQ-EX-1, REQ-EX-2, REQ-EX-3.
 * This class simulates sending notifications to the user or system logs.
 */
public class SystemNotificationService {

    /**
     * Notifies about a successful operation.
     * @param message The success message.
     */
    public void notifySuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    /**
     * Notifies about an error during an operation.
     * @param message The error message.
     */
    public void notifyError(String message) {
        System.err.println("[ERROR] " + message);
    }

    /**
     * Notifies about a cancelled operation.
     * @param message The cancellation message.
     */
    public void notifyCancellation(String message) {
        System.out.println("[INFO] " + message);
    }
}