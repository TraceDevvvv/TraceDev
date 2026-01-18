package com.example.service;

/**
 * Service for displaying notifications to the user.
 */
public class NotificationService {
    
    /**
     * Show error notification to user.
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println("ERROR: " + message);
        // In a real application, this would show a UI notification
    }

    /**
     * Dismiss the current notification.
     */
    public void dismiss() {
        System.out.println("Notification dismissed");
        // In a real application, this would hide the UI notification
    }
}