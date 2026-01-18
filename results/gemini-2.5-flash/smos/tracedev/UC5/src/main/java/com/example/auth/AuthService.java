package com.example.auth;

/**
 * Service for handling authentication and authorization concerns (R3).
 * In a real application, this would integrate with a security framework.
 */
public class AuthService {

    // Simulates the login state of an administrator.
    private boolean administratorLoggedIn = true; // Default to true for the main flow

    /**
     * Sets the simulated login status of the administrator.
     * @param loggedIn True if an administrator is considered logged in, false otherwise.
     */
    public void setAdministratorLoggedIn(boolean loggedIn) {
        this.administratorLoggedIn = loggedIn;
    }

    /**
     * Checks if an administrator is currently logged in.
     * This is an added check to satisfy requirement R3.
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isAdministratorLoggedIn() {
        System.out.println("[AuthService] Checking if administrator is logged in...");
        boolean result = administratorLoggedIn;
        System.out.println("[AuthService] Administrator logged in: " + result);
        return result;
    }
}