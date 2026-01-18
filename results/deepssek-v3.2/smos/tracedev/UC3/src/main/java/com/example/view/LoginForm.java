package com.example.view;

import com.example.controller.FrontController;
import com.example.model.HttpRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the UI login form.
 */
public class LoginForm {
    private String usernameField; // Simplified from TextField for demo
    private String passwordField; // Simplified from TextField for demo
    private Object loginButton;   // Placeholder for Button
    private Object logoutButton;  // Placeholder for Button

    /**
     * Displays the login form.
     */
    public void display() {
        System.out.println("LoginForm displayed.");
    }

    /**
     * Hides the login form.
     */
    public void hide() {
        System.out.println("LoginForm hidden.");
    }

    /**
     * Gets the username input from the form.
     * @return the username as a string.
     */
    public String getUsernameInput() {
        return usernameField;
    }

    /**
     * Gets the password input from the form.
     * @return the password as a string.
     */
    public String getPasswordInput() {
        return passwordField;
    }

    /**
     * Handles a click on the logout button as per the sequence diagram.
     * Generates an HTTP request and forwards it to the FrontController.
     */
    public void handleLogoutClick() {
        System.out.println("LoginForm: Logout button clicked.");
        // Generate a POST /logout HTTP request as per sequence diagram
        HttpRequest request = new HttpRequest();
        request.method = "POST";
        request.path = "/logout";
        request.parameters = new HashMap<>();
        request.headers = new HashMap<>();
        // Assume session token is in headers
        request.headers.put("session-token", "sample-token-123");

        // Route the request to FrontController
        FrontController controller = new FrontController();
        controller.routeRequest(request);
    }

    /**
     * Simulates the user clicking logout button as per sequence diagram message.
     */
    public void clicksLogoutButton() {
        System.out.println("LoginForm: User clicks logout button.");
        handleLogoutClick();
    }
}