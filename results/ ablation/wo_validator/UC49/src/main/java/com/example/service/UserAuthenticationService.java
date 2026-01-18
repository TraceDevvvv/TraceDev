package com.example.service;

/**
 * Service for user authentication.
 */
public class UserAuthenticationService {
    /**
     * Checks if a user is authenticated.
     * @param userId the user ID
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated(String userId) {
        // Simplified authentication logic
        // In real scenario, would check session, tokens, etc.
        return userId != null && !userId.trim().isEmpty();
    }
}