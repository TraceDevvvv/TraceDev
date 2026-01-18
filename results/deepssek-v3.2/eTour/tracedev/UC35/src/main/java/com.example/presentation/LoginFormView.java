package com.example.presentation;

import com.example.application.LoginCommand;

/**
 * View representing the login form.
 */
public class LoginFormView {
    private String usernameField;
    private String passwordField;

    public LoginFormView() {
        // Initialize form fields
        this.usernameField = "";
        this.passwordField = "";
    }

    /**
     * Renders the login form (UI logic placeholder).
     */
    public void render() {
        System.out.println("Rendering login form...");
        // In a real application, this would update the UI
    }

    /**
     * Gets the form data as a LoginCommand.
     *
     * @return the login command populated with form data
     */
    public LoginCommand getFormData() {
        // Assumption: Form fields have been populated by user input
        return new LoginCommand(usernameField, passwordField);
    }

    // Setters for form fields (simulating user input)
    public void setUsernameField(String username) {
        this.usernameField = username;
    }

    public void setPasswordField(String password) {
        this.passwordField = password;
    }
}