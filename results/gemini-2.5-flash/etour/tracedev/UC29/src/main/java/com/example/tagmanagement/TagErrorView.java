package com.example.tagmanagement;

import java.util.Scanner; // Required for simulating user input

/**
 * Represents the view component responsible for displaying tag-related error messages
 * and requesting user confirmation.
 */
public class TagErrorView {
    private String errorMessage; // Stores the current error message to be displayed.
    // The view needs to communicate back to the controller.
    // In a real UI framework, this would be an event listener.
    // Here, we'll pass the controller reference or a callback interface.
    private TagManagementController controller;

    /**
     * Constructs a TagErrorView with a given controller for callbacks.
     *
     * @param controller The TagManagementController to which this view will report user actions.
     */
    public TagErrorView(TagManagementController controller) {
        this.controller = controller;
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void displayError(String message) {
        this.errorMessage = message;
        System.out.println("\n=== ERROR NOTIFICATION ===");
        System.out.println("Error: " + errorMessage);
        System.out.println("============================");
        // Note 1: User not the error message.
    }

    /**
     * Simulates the user confirming that they have read the error message.
     * This method waits for user input.
     *
     * @return true if the user confirmed, false otherwise (though currently always returns true after prompt).
     */
    public boolean requestConfirmation() {
        // Note 2: User asks for confirmation of its reading.
        System.out.print("Please confirm you have read the error message (type 'y' and press Enter): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Read user input
        // Simulate confirmation regardless of input for this simple example,
        // but could add validation.
        System.out.println("User confirmed reading.");

        // Immediately notify the controller that the user confirmed reading.
        // This is the interaction: View -> Controller : errorConfirmed()
        if (controller != null) {
            controller.errorConfirmed();
        } else {
            System.err.println("Error: Controller not set for TagErrorView callback. Cannot notify confirmation.");
        }

        return input.equalsIgnoreCase("y"); // Return true if 'y' was entered, false otherwise.
    }
}