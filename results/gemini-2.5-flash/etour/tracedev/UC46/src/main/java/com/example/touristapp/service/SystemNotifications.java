package com.example.touristapp.service;

/**
 * Service for displaying system-wide notifications.
 * This class abstracts how notifications are shown to the user (e.g., pop-ups, toast messages).\n */
public class SystemNotifications {

    /**
     * Displays a success notification to the user.\n     * @param message The success message to display.\n     */
    public void displaySuccess(String message) {
        System.out.println("\n[SYSTEM NOTIFICATION] SUCCESS: " + message);
        // In a real UI, this would trigger a visual success message.
    }

    /**
     * Displays an error notification to the user.\n     * @param message The error message to display.\n     */
    public void displayError(String message) {
        System.err.println("\n[SYSTEM NOTIFICATION] ERROR: " + message);
        // In a real UI, this would trigger a visual error message.
    }
}