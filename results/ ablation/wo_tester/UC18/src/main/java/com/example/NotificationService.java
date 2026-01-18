package com.example;

/**
 * Service to show notifications to the user.
 * Simulates UI notifications in console.
 */
public class NotificationService {
    /**
     * Shows an error message.
     * @param message The error message to show.
     */
    public void showErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    /**
     * Shows a success message.
     * @param message The success message to show.
     */
    public void showSuccessMessage(String message) {
        System.out.println("[SUCCESS] " + message);
    }
}