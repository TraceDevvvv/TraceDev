package com.example.service;

/**
 * Service for handling user authentication.
 * Added to satisfy requirement Entry Conditions: Agency IS logged in.
 */
public class AuthenticationService {

    private boolean authenticated = false; // Internal state for demonstration

    /**
     * Checks if a user is currently authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        // Dummy implementation: returns the internal state
        return authenticated;
    }

    /**
     * Attempts to authenticate a user with provided credentials.
     * @param username The username.
     * @param password The password.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // Dummy implementation: hardcoded credentials for demonstration
        if ("agency".equals(username) && "password".equals(password)) {
            System.out.println("AuthenticationService: User '" + username + "' authenticated successfully.");
            authenticated = true;
            return true;
        } else {
            System.out.println("AuthenticationService: Authentication failed for user '" + username + "'.");
            authenticated = false;
            return false;
        }
    }
}