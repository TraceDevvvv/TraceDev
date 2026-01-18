package com.example.preferences;

/**
 * A service to handle authentication-related tasks, specifically for retrieving the current tourist ID.
 * This is a placeholder implementation.
 */
public class AuthenticationService {

    // Dummy authenticated tourist ID for demonstration
    private String authenticatedTouristId = "tourist123";
    private boolean authenticated = true;

    /**
     * Retrieves the ID of the currently authenticated tourist.
     * In a real application, this would query the security context.
     * @return The authenticated tourist's ID.
     */
    public String getCurrentTouristId() {
        if (authenticated) {
            return authenticatedTouristId;
        }
        return null; // Or throw an exception if not authenticated
    }

    /**
     * Checks if a tourist is currently authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sets the authenticated tourist ID for testing purposes.
     * @param touristId The tourist ID to set as authenticated.
     */
    public void setAuthenticatedTouristId(String touristId) {
        this.authenticatedTouristId = touristId;
        this.authenticated = true;
    }

    /**
     * Sets the authentication status for testing purposes.
     * @param authenticated The authentication status.
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}