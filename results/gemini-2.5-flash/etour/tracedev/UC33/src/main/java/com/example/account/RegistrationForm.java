package com.example.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simulates the Registration Form, acting as the boundary (View) for user interaction.
 * It displays information, collects user input, and interacts with the controller.
 */
public class RegistrationForm {
    private RegistrationController controller; // Dependency on Controller for interactions

    // Assume these are internal state for a real UI component
    private String usernameInput;
    private String emailInput;
    private String passwordInput;

    /**
     * Sets the controller that this form will interact with.
     * This is often done via dependency injection or a setup method.
     *
     * @param controller The RegistrationController instance.
     */
    public void setController(RegistrationController controller) {
        this.controller = controller;
    }

    /**
     * Displays the registration form (simulated by printing to console).
     */
    public void display() {
        System.out.println("--- Register New Account ---");
        System.out.println("Please enter your details:");
    }

    /**
     * Simulates getting data from the form fields.
     * In a real application, this would read from actual UI components.
     * For this simulation, we'll return hardcoded data or prompt for it.
     *
     * @return A RegistrationRequestDTO populated with user input.
     */
    public RegistrationRequestDTO getData() {
        // For demonstration, use a scanner or hardcoded values
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        usernameInput = scanner.nextLine();
        System.out.print("Email: ");
        emailInput = scanner.nextLine();
        System.out.print("Password: ");
        passwordInput = scanner.nextLine();
        // A real application would not close the scanner here if it's reused.
        // For simple demonstration, we'll assume a new scanner or reuse.

        return new RegistrationRequestDTO(usernameInput, emailInput, passwordInput);
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to display.
     */
    public void showMessage(String message) {
        System.out.println("[INFO]: " + message);
    }

    /**
     * Asks for confirmation from the user. This method corresponds to message `m11`
     * from the Presenter to the Form, and handles messages `m12` and `m13`
     * (Form to GuestUser and GuestUser to Form).
     *
     * @param message The confirmation message to display.
     * @return true if the user confirms, false otherwise.
     */
    public boolean askConfirmation(String message) { // Renamed from requestConfirmation to match sequence diagram m11
        System.out.println("[CONFIRMATION]: " + message + " (yes/no)"); // m12: Asks for confirmation
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase(); // m13: GuestUser confirms the operation
        return "yes".equals(input);
    }

    /**
     * Displays validation errors to the user.
     *
     * @param errors A list of error messages.
     */
    public void displayErrors(List<String> errors) {
        System.err.println("[ERRORS]: The following issues were found:");
        for (String error : errors) {
            System.err.println(" - " + error);
        }
    }

    /**
     * Notifies the user that the registration process has been cancelled.
     * (Added to satisfy REQ-014)
     */
    public void notifyCancellation() {
        System.out.println("[INFO]: Registration process cancelled.");
    }
}