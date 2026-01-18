package com.example.login;

/**
 * Presentation Layer: Represents the user interface for login.
 * Handles input and displays errors to the user.
 */
public class LoginForm {
    private String usernameInput;
    private String passwordInput;

    /**
     * Constructs a new LoginForm with the provided username and password inputs.
     * @param usernameInput The username entered by the user.
     * @param passwordInput The password entered by the user.
     */
    public LoginForm(String usernameInput, String passwordInput) {
        this.usernameInput = usernameInput;
        this.passwordInput = passwordInput;
    }

    /**
     * Retrieves the username entered in the form.
     * @return The username.
     */
    public String getUsername() {
        return usernameInput;
    }

    /**
     * Retrieves the password entered in the form.
     * @return The password.
     */
    public String getPassword() {
        return passwordInput;
    }

    /**
     * Displays an error message to the user.
     * // Added to satisfy requirement R8, R9, R10
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.out.println("Login Form Error: " + message);
    }
}