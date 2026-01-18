package com.example.service;

/**
 * Service for authentication and session management.
 */
public class AuthenticationService {
    private String currentUserId = "user123"; // Simulated logged-in user

    /**
     * Checks if the user is logged in.
     * @param userId the user id to check
     * @return true if logged in
     */
    public boolean isLoggedIn(String userId) {
        return currentUserId != null && currentUserId.equals(userId);
    }

    /**
     * Gets the current logged-in user.
     * @return the user id
     */
    public String getCurrentUser() {
        return currentUserId;
    }

    /**
     * Verifies the session (for sequence diagram).
     * @return true if session is valid
     */
    public boolean verifySession() {
        return currentUserId != null;
    }
}