package com.example.presentation;

/**
 * Represents the user (agency operator) who interacts with the system.
 * Part of the Presentation Layer in the Class Diagram.
 */
public class AgencyOperator {
    private String username;

    public AgencyOperator(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Returns true if the operator is authenticated.
     * Simplified for this example.
     */
    public boolean isAuthenticated() {
        // In a real scenario, authentication state would be checked.
        return true;
    }
}