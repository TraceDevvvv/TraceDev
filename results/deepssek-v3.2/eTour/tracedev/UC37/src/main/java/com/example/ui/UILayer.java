package com.example.ui;

import com.example.controller.LogoutController;
import com.example.model.RegisteredUser;

/**
 * Simulated UI layer for the logout functionality.
 */
public class UILayer {
    private LogoutController controller;

    public UILayer(LogoutController controller) {
        this.controller = controller;
    }

    /**
     * Entry point for logout from the UI (Step 1 in sequence diagram).
     * @param userId The user ID.
     */
    public void accessLogoutFunctionality(String userId) {
        // Step 1: initiate logout
        controller.accessesLogoutFunctionality(userId);
    }

    public void displayConfirmationDialog(String userId) {
        // This method corresponds to sequence message "display confirmation dialog"
        controller.displayConfirmationDialog(userId);
    }

    public void confirmLogoutRequest(String userId) {
        // This method corresponds to sequence message "confirm logout request"
        controller.confirmLogoutRequest(userId);
    }

    public void displaySuccessNotification() {
        // This method corresponds to sequence message "display success notification"
        controller.displaySuccessNotification("Logout completed successfully.");
    }

    public void displayErrorMessage(String message) {
        // This method corresponds to sequence message "display error message"
        controller.displayErrorMessage(message);
    }

    public RegisteredUser getUserEntity(String userId) {
        // This method corresponds to sequence message "UserEntity" return
        return controller.getUserEntity(userId);
    }

    private void simulateUserConfirmation(String userId) {
        // Simulate user confirming the logout
        boolean confirmed = controller.confirmLogout(userId);
        if (confirmed) {
            displaySuccessNotification();
        } else {
            displayError("Logout confirmation failed.");
        }
    }

    /**
     * Displays a success notification.
     */
    public void displaySuccessNotification(String message) {
        System.out.println("Success notification: " + message);
    }

    /**
     * Displays an error message.
     * @param message The error message.
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}