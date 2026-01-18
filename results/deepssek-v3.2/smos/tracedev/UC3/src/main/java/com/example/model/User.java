package com.example.model;

/**
 * Represents a registered user of the system.
 */
public class User {
    public String username;
    public String sessionToken;

    /**
     * Simulates a user login attempt.
     * @return true if login is successful.
     */
    public boolean login() {
        // In a real system, this would validate credentials
        System.out.println("User " + username + " logging in.");
        return true;
    }

    /**
     * Simulates a user logout.
     */
    public void logout() {
        System.out.println("User " + username + " logging out.");
    }
}