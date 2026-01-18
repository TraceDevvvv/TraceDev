package com.example.app;

/**
 * Manages the authentication state of the administrator.
 * This class corresponds to the 'SessionManager' class in the UML Class Diagram (R3).
 * For demonstration purposes, authentication status can be toggled.
 */
public class SessionManager {
    // Assumption: Default authentication status is true for happy path demonstration
    private boolean authenticated = true;

    /**
     * Checks if an administrator is currently authenticated.
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        System.out.println("SessionManager: Checking authentication status. Authenticated: " + authenticated);
        return authenticated;
    }

    /**
     * Sets the authentication status for testing purposes.
     * @param authenticated The desired authentication status.
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        System.out.println("SessionManager: Authentication status set to " + authenticated);
    }
}