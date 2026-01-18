package com.example;

// Actor class representing a User in the system
public class User {
    private String userId;
    private boolean isAuthenticated;

    public User(String userId) {
        this.userId = userId;
        this.isAuthenticated = false;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    // Method to authenticate the user, returns authentication status
    public boolean authenticate() {
        // In a real system, this would call an authentication service
        // For demo purposes, assume authentication succeeds if userId is not empty
        this.isAuthenticated = userId != null && !userId.trim().isEmpty();
        return this.isAuthenticated;
    }
}