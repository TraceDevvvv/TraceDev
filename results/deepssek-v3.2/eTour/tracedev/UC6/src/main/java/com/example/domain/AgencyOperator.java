package com.example.domain;

/**
 * Represents the agency operator actor.
 */
public class AgencyOperator {
    private String username;
    private boolean isLoggedIn;

    public boolean login(String username, String password) {
        // Simplified login logic: assume success for demonstration
        this.username = username;
        this.isLoggedIn = true;
        return true;
    }

    public void logout() {
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}