package com.example.service;

/**
 * Service for checking user authentication.
 */
public class AuthenticationService {
    /**
     * Checks if the user is authenticated.
     * @param user the user identifier
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated(String user) {
        // For simplicity, assume all users are authenticated.
        // In a real application, this would check against an authentication system.
        return true;
    }
}