package com.example.model;

/**
 * Represents a user in the system.
 * Contains user identifier and role information.
 */
public class User {
    private String userId;
    private String role;

    public User(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}