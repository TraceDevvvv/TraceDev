package com.example.service;

/**
 * Service for authentication checks.
 * Stereotype <<reliable>> - must maintain 99.9% availability and max response time < 2s.
 */
public class AuthenticationService {

    /**
     * Checks if the given agency is logged in.
     */
    public boolean isLoggedIn(String agency) {
        // Simplified: always return true for simulation.
        return true;
    }

    /**
     * Verifies the login status of the current user.
     * @return true if logged in, false otherwise.
     */
    public boolean verifyLogin() {
        // In a real system, this would check session or token.
        // For simulation, we assume the user is logged in.
        return true;
    }
}