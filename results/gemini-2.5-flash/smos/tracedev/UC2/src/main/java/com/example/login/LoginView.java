package com.example.login;

/**
 * Represents the user interface for login operations.
 * It's responsible for displaying the login form and authentication errors.
 */
public class LoginView {

    /**
     * Displays the login form to the user.
     * --- Implements: LoginView.displayLoginForm() from Class Diagram and Sequence Diagram ---
     */
    public void displayLoginForm() {
        // --- Sequence Diagram: Note 'System displays the login form.' (m8) ---
        System.out.println("LoginView: Displaying login form...");
        System.out.println("------------------------------------");
        System.out.println("|           LOGIN                  |");
        System.out.println("| Username: [                ]     |");
        System.out.println("| Password: [                ]     |");
        System.out.println("|                                  |");
        System.out.println("|           [  Login  ]            |");
        System.out.println("------------------------------------");
        // In a real application, this would render a UI component.
    }

    /**
     * Displays an authentication error message to the user.
     * --- Implements: LoginView.displayAuthenticationError(errorMessage : String) from Class Diagram and Sequence Diagram ---
     *
     * @param errorMessage The specific error message to display.
     */
    public void displayAuthenticationError(String errorMessage) {
        // --- Sequence Diagram: Note 'System clearly informs user about incorrect authentication data.' (m5) ---
        System.err.println("LoginView: Displaying authentication error: " + errorMessage);
        System.out.println("--- Please try again. ---");
        // In a real application, this would update a UI element with the error.
    }
}