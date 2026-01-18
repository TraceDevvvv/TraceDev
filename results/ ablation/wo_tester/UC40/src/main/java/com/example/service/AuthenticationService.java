package com.example.service;

import com.example.entity.PointOfRestaurantOperator;

/**
 * Authentication service for validating user sessions.
 */
public class AuthenticationService {
    /**
     * Validates a session token.
     * Simplified implementation - in reality would verify token signature and expiry.
     * @param token the session token
     * @return true if token is valid, false otherwise
     */
    public boolean validateSession(String token) {
        // Simplified: check if token is not null and not empty
        return token != null && !token.trim().isEmpty();
    }

    /**
     * Gets the current authenticated user.
     * Simplified implementation - would typically decode token to get user info.
     * @return the current authenticated operator
     */
    public PointOfRestaurantOperator getCurrentUser() {
        // Simplified: return a mock user
        return new PointOfRestaurantOperator("operator123", "John Doe");
    }
}