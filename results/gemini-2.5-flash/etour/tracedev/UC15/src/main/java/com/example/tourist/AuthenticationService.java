package com.example.tourist;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Mock Authentication Service to satisfy entry conditions.
 * In a real application, this would interact with an authentication provider.
 */
public class AuthenticationService {
    private boolean loggedIn = false;
    private User currentUser = null;

    /**
     * Simulates a user login attempt.
     * @param username The username.
     * @param password The password.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        System.out.println("[AuthenticationService] Attempting login for: " + username);
        // Simple mock login logic
        if ("operator".equals(username) && "password123".equals(password)) {
            this.loggedIn = true;
            this.currentUser = new User(username, Arrays.asList("AGENCY_OPERATOR", "USER"));
            System.out.println("[AuthenticationService] Login successful.");
            return true;
        }
        System.out.println("[AuthenticationService] Login failed.");
        this.loggedIn = false;
        this.currentUser = null;
        return false;
    }

    /**
     * Checks if a user is currently logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns the currently logged-in user.
     * @return The User object if logged in, null otherwise.
     */
    public User getCurrentUser() {
        return currentUser;
    }
}