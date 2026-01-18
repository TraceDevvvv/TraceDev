package com.etour.login.ui;

import com.etour.login.controller.LoginController;
import com.etour.login.dto.LoginRequestDTO;
import com.etour.login.dto.LoginResponseDTO;
import com.etour.login.exception.ETOURConnectionException;

/**
 * UI component for the login form.
 * Simulates a graphical login form.
 */
public class LoginForm {
    private String usernameField;
    private String passwordField;
    private LoginController loginController;

    public LoginForm(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Displays the login form.
     * Added to satisfy requirement REQ-007.
     */
    public void displayLoginForm() {
        System.out.println("=== Login Form Displayed ===");
        System.out.println("Please enter your credentials:");
        // In a real UI, this would render the form components
    }

    /**
     * Activates the login feature.
     * Corresponds to sequence diagram message "1. Activate login feature"
     */
    public void activateLoginFeature() {
        System.out.println("Login feature activated");
        displayLoginForm();
    }

    /**
     * Fills and submits form with username and password.
     * Corresponds to sequence diagram message "3. Fill and submit form\n(with username & password)"
     *
     * @param username the username
     * @param password the password
     */
    public void fillAndSubmitForm(String username, String password) {
        this.usernameField = username;
        this.passwordField = password;
        LoginRequestDTO credentials = new LoginRequestDTO(username, password);
        submitCredentials(credentials);
    }

    /**
     * Submits credentials to the controller.
     * Main flow method for requirement REQ-005.
     *
     * @param credentials the login request DTO containing username and password
     */
    public void submitCredentials(LoginRequestDTO credentials) {
        System.out.println("Submitting credentials for user: " + credentials.getUsername());
        LoginResponseDTO response;
        try {
            response = loginController.handleLoginRequest(credentials);
        } catch (ETOURConnectionException e) {
            showConnectionError(e.getMessage());
            return;
        }
        
        if (response.isSuccess()) {
            redirectToWorkspace();
        } else {
            displayErrorMessage(response.getErrorMessage());
        }
    }

    /**
     * Displays an error message on the form.
     * Error handling for requirement REQ-009.
     *
     * @param message the error message to display
     */
    public void displayErrorMessage(String message) {
        System.out.println("ERROR: " + message);
        // In a real UI, this would update the form with the error message
    }

    /**
     * Shows connection error.
     * Corresponds to sequence diagram message "Show connection error"
     *
     * @param message the connection error message
     */
    public void showConnectionError(String message) {
        System.out.println("CONNECTION ERROR: " + message);
        // In a real UI, this would show a connection error dialog
    }

    /**
     * Redirects to the workspace after successful login.
     * Corresponds to sequence diagram message "10. Display workspace area"
     */
    public void redirectToWorkspace() {
        System.out.println("=== Redirecting to Workspace ===");
        System.out.println("Welcome! You now have access to the workspace area.");
        // In a real application, this would navigate to the main application interface
    }

    /**
     * Cancels the login operation.
     * Corresponds to sequence diagram message "Cancel"
     */
    public void cancel() {
        closeForm();
    }

    /**
     * Closes the form.
     * Corresponds to sequence diagram message "Close form"
     */
    public void closeForm() {
        System.out.println("Form closed");
        // In a real UI, the form window would close
    }

    // Simulate form field updates
    public void setUsernameField(String username) {
        this.usernameField = username;
    }

    public void setPasswordField(String password) {
        this.passwordField = password;
    }

    public LoginRequestDTO createLoginRequest() {
        return new LoginRequestDTO(usernameField, passwordField);
    }
}