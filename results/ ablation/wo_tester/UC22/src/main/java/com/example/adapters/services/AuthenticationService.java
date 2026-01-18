package com.example.adapters.serv;

/**
 * Simulates authentication service.
 * Precondition: Actor is authenticated (as per sequence diagram).
 */
public class AuthenticationService {
    private String currentUser = "operator123"; // Simulated authenticated user
    private boolean isAuthenticated = true;

    public boolean isLoggedIn() {
        return isAuthenticated;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    // Methods for testing or simulation
    public void setCurrentUser(String user) {
        this.currentUser = user;
    }

    public void setAuthenticated(boolean authenticated) {
        this.isAuthenticated = authenticated;
    }
}