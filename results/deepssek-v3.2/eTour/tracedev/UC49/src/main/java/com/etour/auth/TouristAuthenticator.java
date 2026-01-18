package com.etour.auth;

/**
 * Simulates authentication and provides authenticated user ID.
 */
public class TouristAuthenticator {
    // Simulated authenticated user ID (in real app, this would come from session/token).
    private static final int AUTHENTICATED_USER_ID = 1001;
    private boolean authenticated = true; // default to true for simulation

    /**
     * Returns the authenticated user ID.
     * In a real application, this would extract ID from security context.
     */
    public int getAuthenticatedUserId() {
        return AUTHENTICATED_USER_ID;
    }

    /**
     * Checks if the user is authenticated.
     */
    public boolean isAuthenticated() {
        // Simulate authentication check; could be turned off for testing.
        return authenticated;
    }

    // For testing: set authentication status
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}