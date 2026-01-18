package com.example.session;

/**
 * Represents a user session.
 */
public class UserSession {
    private int userId;
    private String username;
    private String role;

    public UserSession(int userId, String username, String role) {
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}