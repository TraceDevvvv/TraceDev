package com.example.service;

/**
 * Service to handle user authentication.
 */
public class AuthenticationService {
    
    // Simulating an authenticated user state.
    private boolean isAuthenticated = false;
    
    /**
     * Authenticate a user (simulated for this example).
     * @param touristId The ID of the tourist.
     * @return true if authentication succeeds, false otherwise.
     */
    public boolean authenticate(String touristId) {
        // Simulated authentication logic.
        if (touristId != null && !touristId.isEmpty()) {
            isAuthenticated = true;
            return true;
        }
        return false;
    }
    
    /**
     * Check if the current tourist is authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    
    /**
     * Log out the current tourist.
     */
    public void logout() {
        isAuthenticated = false;
    }
}