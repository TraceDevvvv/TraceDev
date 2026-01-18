package com.example.user;

/**
 * Represents a user entity with identification and role information.
 */
public class User {
    private final String userId;
    private final String username;
    private final String role;

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