package com.example.pointofrest;

/**
 * Service for handling user authentication.
 * Added to satisfy requirement REQ-001 (Entry Conditions: Agency IS logged in.)
 */
public class AuthenticationService {

    private boolean authenticated = true; // Default to true for simulation
    private AgencyOperator loggedInUser = new AgencyOperator("agency123", "TravelAgencyInc"); // Dummy user

    /**
     * Checks if a user is currently authenticated.
     *
     * @return true if a user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        // Simulate authentication status
        System.out.println("[AuthenticationService] Checking authentication status: " + authenticated);
        return authenticated;
    }

    /**
     * Retrieves the currently logged-in agency operator.
     *
     * @return The AgencyOperator object if logged in, null otherwise.
     */
    public AgencyOperator getLoggedInUser() {
        // Simulate returning a logged-in user
        if (authenticated) {
            System.out.println("[AuthenticationService] Returning logged-in user: " + loggedInUser.username);
            return loggedInUser;
        }
        System.out.println("[AuthenticationService] No user logged in.");
        return null;
    }

    /**
     * Setter to simulate authentication status for testing purposes.
     * @param authenticated The authentication status to set.
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}