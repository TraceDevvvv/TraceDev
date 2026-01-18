package com.example.service;

/**
 * Service class for authentication (mentioned in class diagram but not in sequence).
 * Corresponds to the AuthenticationService class in the UML diagram.
 */
public class AuthenticationService {

    /**
     * Default constructor.
     */
    public AuthenticationService() {
    }

    /**
     * Checks if a user is authenticated.
     * @param userId The user identifier.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated(String userId) {
        // Simplified authentication check.
        return userId != null && !userId.trim().isEmpty();
    }
}