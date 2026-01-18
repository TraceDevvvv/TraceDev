package com.example.reportcard;

/**
 * Handles presenting confirmation forms and success messages to the user.
 * Simulates UI interactions through console output.
 */
public class ConfirmationPresenter {

    /**
     * Displays a confirmation form with a given message to the user.
     * This simulates a UI prompt for user confirmation.
     *
     * @param message The message to display in the confirmation form.
     */
    public void displayConfirmationForm(String message) {
        System.out.println("\n--- Confirmation Required ---");
        System.out.println("User Interface: " + message);
        System.out.println("User Interface: (Simulating 'Yes/No' prompt)");
        System.out.println("---------------------------\n");
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("\n--- Success ---");
        System.out.println("User Interface: " + message);
        System.out.println("-----------------\n");
    }

    /**
     * Displays the class list form to the user.
     * This simulates navigating back to a list view in the UI.
     */
    public void displayClassListForm() {
        System.out.println("\n--- Navigation ---");
        System.out.println("User Interface: Displaying the class list form.");
        System.out.println("------------------\n");
    }
}