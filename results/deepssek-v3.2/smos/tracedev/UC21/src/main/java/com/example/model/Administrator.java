package com.example.model;

import com.example.controller.TeachingAssignmentController;

/**
 * Represents an administrator who can interact with the teaching assignment system.
 * Based on the UML class diagram.
 */
public class Administrator {
    private String adminId;
    private String username;
    private String passwordHash;

    public Administrator(String adminId, String username, String passwordHash) {
        this.adminId = adminId;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public boolean login(String username, String password) {
        // Simplified login logic: check if username matches and password hash matches (in real scenario, hash the password)
        return this.username.equals(username) && this.passwordHash.equals(password);
    }

    public void logout() {
        // Perform logout actions (e.g., clear session)
        System.out.println("Administrator " + username + " logged out.");
    }

    // Getters and setters
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
}