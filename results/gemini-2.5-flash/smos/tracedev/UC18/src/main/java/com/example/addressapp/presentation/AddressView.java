package com.example.addressapp.presentation;

import java.util.Scanner; // For simulating user input in confirmation dialog

/**
 * Represents the user interface for address management.
 * This class is responsible for displaying forms, messages, and handling
 * simple user interactions (like confirmation dialogs).
 */
public class AddressView {

    /**
     * Displays a form for entering a new address.
     * In a real UI, this would render a GUI form.
     */
    public void showNewAddressForm() {
        System.out.println("\n--- Address Form ---");
        System.out.println("Please enter address details.");
        // Placeholder for rendering a UI form
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n[ERROR] " + message);
    }

    /**
     * Displays a success message to the user.
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("\n[SUCCESS] " + message);
    }

    /**
     * Displays a confirmation dialog to the user and waits for input.
     * This method simulates a blocking UI dialog.
     *
     * @param message The message to show in the confirmation dialog.
     * @return true if the user confirms (e.g., clicks OK/Yes), false otherwise.
     */
    public boolean displayConfirmationDialog(String message) {
        System.out.println("\n[CONFIRMATION] " + message + " (Type 'yes' to confirm, any other key to cancel)");
        // Simulate user input for confirmation
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            return input.trim().equalsIgnoreCase("yes");
        } catch (Exception e) {
            System.err.println("Error reading input for confirmation: " + e.getMessage());
            return false; // Default to false on error
        }
    }
}