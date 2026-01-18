package com.example.entities;

/**
 * Represents a user in the system.
 * Entry Condition: The user IS logged in.
 */
public class User {
    private String userId;
    private String username;
    private String role;

    public User(String userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}