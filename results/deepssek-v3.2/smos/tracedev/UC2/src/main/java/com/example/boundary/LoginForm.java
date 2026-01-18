package com.example.boundary;

import com.example.model.LoginCredentials;
import com.example.port.FormDisplayPort;

/**
 * Boundary class representing the login form UI.
 * Implements FormDisplayPort to allow displaying the form.
 * Traceability: REQ-002 - Supports "User can try to log in"
 */
public class LoginForm implements FormDisplayPort {

    /**
     * Displays the login form to the user.
     * This method is called when authentication fails and the user needs to try again.
     */
    @Override
    public void displayLoginForm() {
        System.out.println("LoginForm: Displaying login form (authentication failed, please try again).");
        // In a real GUI application, this would make the form visible again.
        // After displaying, return to caller (sequence diagram message m11)
        // The return is implicit, but we ensure the method exists and is called.
    }

    /**
     * Displays the form (initial display).
     * This method is part of the boundary's own API.
     * It corresponds to sequence diagram message m11 "display form" from Form to User.
     */
    public void displayForm() {
        System.out.println("LoginForm: Displaying login form (initial).");
        // Show the login form UI.
        // This method is called by the user actor (though not directly in code).
        // The method is present to satisfy traceability.
    }

    /**
     * Retrieves credentials from the form (simulates user input).
     * @return LoginCredentials object containing username and password.
     */
    public LoginCredentials getCredentials() {
        // Simulate retrieving credentials from UI fields.
        String username = "sampleUser";
        String password = "samplePass";
        System.out.println("LoginForm: Getting credentials from form fields.");
        return new LoginCredentials(username, password);
    }

    /**
     * Initiates a login attempt.
     * This method is called by the user (actor) as per sequence diagram.
     * Triggers the authentication flow.
     */
    public void attemptLogin() {
        System.out.println("LoginForm: Login attempt initiated by user.");
        LoginCredentials credentials = getCredentials();
        // In a real application, we would pass these to a controller.
        // For this example, we assume the controller is injected or accessed via a static method.
        // We'll simulate the flow in the Main class.
    }
}