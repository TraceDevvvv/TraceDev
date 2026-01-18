package com.example.ui;

import com.example.dto.LoginResponse;
import com.example.entity.User;
import com.example.manager.SessionManager;

/**
 * Boundary class for the login user interface.
 */
public class LoginUI {
    private SessionManager sessionManager;

    public LoginUI(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Displays the login form.
     * In a real UI, this would render the form.
     */
    public void displayLoginForm() {
        System.out.println("Login form displayed.");
    }

    /**
     * Displays the user's workspace after successful login.
     */
    public void displayWorkspace(User user) {
        if (user != null) {
            System.out.println("Displaying workspace for user: " + user.getUsername());
        }
    }

    /**
     * Displays an error message to the user.
     */
    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Handles the login submission from the user.
     * This method simulates the UI initiating the login flow.
     */
    public void handleUserLogin(String username, String password) {
        // Entry condition check: user not logged in
        if (sessionManager.isUserLoggedIn(username)) {
            displayError("User is already logged in.");
            return;
        }

        // Simulate form validation (Entry Conditions second & third)
        if (username == null || username.trim().isEmpty()) {
            displayError("Username cannot be empty.");
            return;
        }
        if (password == null || password.trim().isEmpty()) {
            displayError("Password cannot be empty.");
            return;
        }

        // In a real system, the UI would call the controller.
        // For this example, we directly create the controller and call it.
        // Note: In a clean architecture, UI would delegate to a controller.
        System.out.println("Submitting login form for user: " + username);
        // The actual call to controller is done in the main method.
    }

    public void displayConnectionError() {
        displayError("Server connection error.");
    }

    public void displayValidationError() {
        displayError("Validation error.");
    }

    public void displayAuthenticationError() {
        displayError("Authentication failed.");
    }
}