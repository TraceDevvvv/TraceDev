package com.example.digitalregister.service;

/**
 * A dummy authentication service to check user login status.
 */
public class AuthenticationService {

    /**
     * Checks if a user is logged into the system.
     * This is a dummy implementation for demonstration purposes.
     * @param userId The ID of the user to check.
     * @return True if the user is considered logged in, false otherwise.
     */
    public boolean isLoggedIn(String userId) {
        // Assume 'Direction' is logged in if userId is not null or empty for simplicity.
        // In a real system, this would involve checking session tokens, database, etc.
        boolean loggedIn = userId != null && !userId.trim().isEmpty();
        System.out.println("[AuthenticationService] Checking login status for user: '" + userId + "' - Logged In: " + loggedIn);
        return loggedIn;
    }
}