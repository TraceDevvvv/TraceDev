package com.example.uml;

/**
 * Service for sending notifications and confirmation dialogs.
 * Simulates UI interactions.
 */
public class NotificationService {
    public void sendSuccessMessage(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public void sendErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public boolean displayConfirmation(String message) {
        // Simulate user confirmation; in a real UI, this would show a dialog.
        System.out.println("[CONFIRM] " + message);
        // Assume user confirms.
        return true;
    }
}