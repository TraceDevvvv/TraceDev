package com.example;

/**
 * Represents a User in the system.
 */
public class User {
    private String username;
    private String role;

    // Constructor
    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    // Additional getter for username if needed
    public String getUsername() {
        return username;
    }
}