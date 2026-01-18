package com.example.serv;

/**
 * <<Security>> stereotype.
 * Handles user authentication and authorization.
 */
public class AuthenticationService {
    /**
     * Authenticates a user by userId.
     */
    public boolean isAuthenticated(String userId) {
        // Simplified authentication: assume any non-null userId is authenticated
        return userId != null && !userId.trim().isEmpty();
    }

    /**
     * Gets the current authenticated user.
     */
    public String getCurrentUser() {
        // Simplified: return a dummy user
        return "operator123";
    }
}