package com.example.model;

/**
 * Represents a user in the system.
 */
public class User {
    private String username;
    private String password; // In a real application, this would be hashed.

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}