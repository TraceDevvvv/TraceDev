package com.example.passwordchange;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner; // Added for console input

/**
 * View class responsible for displaying the password change form and handling user input.
 * It also displays error messages and provides a way for the user to acknowledge them.
 */
public class PasswordChangeView {
    // A listener to notify the controller about user actions.
    private PasswordChangeViewListener listener;
    // For console-based input, typically a single scanner instance is used.
    // However, since getUserInput is called only conceptually in this flow,
    // we instantiate it locally for demonstration.
    // In a real application, this would be managed by a UI framework.
    private Scanner scanner = new Scanner(System.in); // Initialized once

    /**
     * Sets the listener for view events.
     *
     * @param listener The object that will handle view events.
     */
    public void setListener(PasswordChangeViewListener listener) {
        this.listener = listener;
    }

    /**
     * Displays the password change form, populating fields based on the provided model.
     *
     * @param model The {@link PasswordChangeModel} containing data to display.
     */
    public void displayForm(PasswordChangeModel model) {
        System.out.println("\n--- Password Change Form ---");
        System.out.println("New Password: [" + (model.getNewPassword() != null ? model.getNewPassword().replaceAll(".", "*") : "") + "]");
        System.out.println("Confirm Password: [" + (model.getConfirmPassword() != null ? model.getConfirmPassword().replaceAll(".", "*") : "") + "]");
        if (model.getCurrentErrorMessage() != null && !model.getCurrentErrorMessage().isEmpty()) {
            System.out.println("Current Error: " + model.getCurrentErrorMessage());
        }
        System.out.println("--------------------------");
        System.out.println("View: Form displayed based on model state.");
    }

    /**
     * Simulates getting user input from the form fields.
     * For this example, it returns a hardcoded map.
     *
     * @return A map containing user input, e.g., "newPassword" and "confirmPassword".
     */
    public Map<String, String> getUserInput() {
        // This method is now fully implemented to read from the console.
        // Note: In the main simulation, controller.simulateUserInput is used,
        // so this method is not actively called there to avoid blocking the automated test.
        System.out.println("View: Please enter password details:");
        System.out.print("Enter New Password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm New Password: ");
        String confirmPassword = scanner.nextLine();

        Map<String, String> input = new HashMap<>();
        input.put("newPassword", newPassword);
        input.put("confirmPassword", confirmPassword);
        System.out.println("View: User input captured.");
        return input;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("View: Displaying Error Message: " + message);
        // In a real UI, this would update a label or show a pop-up.
    }

    /**
     * Simulates the user acknowledging an error message.
     * This method notifies the controller via the listener.
     */
    public void acknowledgeError() {
        System.out.println("View: User action - 'Acknowledge Error' button pressed.");
        if (listener != null) {
            listener.onAcknowledgeError();
        } else {
            System.out.println("View: No listener attached to handle acknowledgeError event.");
        }
    }
}