package com.example.service;

/**
 * Service for authentication checks.
 * Added to satisfy requirement R4 (entry condition check).
 */
public class AuthenticationService {
    /**
     * Checks if a user is logged in.
     */
    public boolean isLoggedIn(String userId) {
        // Simplified implementation; in a real system, this would verify session/token.
        System.out.println("Checking login status for user: " + userId);
        return userId != null && !userId.trim().isEmpty(); // Assume non-empty user ID means logged in.
    }
}