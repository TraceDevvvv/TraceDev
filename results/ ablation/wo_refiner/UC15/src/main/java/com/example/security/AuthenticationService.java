package com.example.security;

/**
 * Authentication service for security checks.
 * Changed stereotype to <<Security>> per REQ-004, REQ-014.
 */
public class AuthenticationService {
    private boolean loggedIn = true; // Default to true for simulation
    
    /**
     * Check if user is logged in.
     * Added to satisfy requirement REQ-004, <<Security>> - REQ-014.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    /**
     * Set login status for simulation.
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}