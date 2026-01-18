package com.example.uml;

/**
 * Represents a generic User in the system.
 */
public class User {
    protected String userId;
    protected String username;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public boolean login() {
        // Simulate login logic
        System.out.println("User " + username + " logged in.");
        return true;
    }

    public void logout() {
        // Simulate logout logic
        System.out.println("User " + username + " logged out.");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}