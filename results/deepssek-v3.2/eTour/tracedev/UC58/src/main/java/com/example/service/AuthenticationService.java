package com.example.service;

import com.example.model.User;

/**
 * Authentication service for entry condition.
 * Manages current user authentication state.
 */
public class AuthenticationService {
    private static AuthenticationService instance;
    private User currentUser;

    private AuthenticationService() {
        // Private constructor for singleton
        currentUser = new User("user123", "tourist", true); // Simulate authenticated user
    }

    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    // Check if current user is authenticated
    public boolean isAuthenticated() {
        return currentUser != null && currentUser.isAuthenticated();
    }

    // Get current user
    public User getCurrentUser() {
        return currentUser;
    }

    // Helper for simulating authentication failure (for testing)
    public void setAuthenticated(boolean authenticated) {
        if (currentUser != null) {
            // In real app, would update user state
        }
    }
}