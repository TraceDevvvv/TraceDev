package com.loginapp.view;

import com.loginapp.model.User;

import java.util.Optional;
import java.util.Scanner;

/**
 * Handles user interaction for the login process, including displaying forms
 * and messages, and capturing user input.
 */
public class LoginView {

    private final Scanner scanner;

    /**
     * Constructs a LoginView with a given Scanner for input.
     *
     * @param scanner The Scanner object to use for reading user input.
     */
    public LoginView(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays the login form to the user.
     */
    public void displayLoginForm() {
        System.out.println("\n--- Login ---");
        System.out.println("Please enter your credentials or type 'cancel' to exit.");
    }

    /**
     * Prompts the user for username and password and returns them as an Optional User object.
     * If the user types 'cancel' for the username, an empty Optional is returned.
     *
     * @return An Optional containing a User object with entered credentials, or an empty Optional if cancelled.
     */
    public Optional<User> getLoginCredentials() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        if (username.equalsIgnoreCase("cancel")) {
            return Optional.empty();
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();

        // In a real application, password input might be masked (e.g., using Console.readPassword())
        // For a simple console app, nextLine() is sufficient.

        return Optional.of(new User(username, password));
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to display.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public void displayErrorMessage(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }
}