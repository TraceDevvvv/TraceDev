package com.example.service;

/**
 * Service responsible for showing notifications and confirmation dialogs.
 * For simplicity, we simulate user interaction via console.
 */
public class NotificationService {
    /**
     * Shows an error message to the user.
     *
     * @param message the error message
     */
    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Shows a confirmation dialog and waits for user confirmation.
     * For simulation, we assume the user confirms by entering 'y' in the console.
     *
     * @param message the confirmation message
     * @return true if the user confirms, false otherwise
     */
    public boolean showConfirmationDialog(String message) {
        System.out.println("CONFIRMATION: " + message + " (y/n)");
        // In a real application, we would have a UI dialog.
        // Here we simulate by reading from console (for simplicity, we assume 'y').
        // For demonstration, we always return true.
        return true;
    }
}