package com.system.serv;

/**
 * Service to display warnings and get user confirmations.
 */
public class NotificationService {
    public void displayWarning(String message) {
        System.out.println("[WARNING] " + message);
        // In a real UI, this would show a dialog.
    }

    public boolean getConfirmation() {
        // Simulate user confirmation. Assume user confirms.
        // In a real system, would wait for user input.
        return true;
    }
}