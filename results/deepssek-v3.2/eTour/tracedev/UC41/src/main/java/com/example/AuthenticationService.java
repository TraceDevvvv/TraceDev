package com.example;

/**
 * AuthenticationService for verifying user authentication.
 */
public class AuthenticationService {
    /**
     * Check if a user is authenticated.
     * @param user The user identifier.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated(String user) {
        // Simplified authentication - assume user "operator" is authenticated.
        return "operator".equals(user);
    }
}