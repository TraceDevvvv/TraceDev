package com.example.model;

/**
 * Represents a user in the system.
 */
public class User {
    private String userId;
    private String passwordHash;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPasswordHash(String hash) {
        this.passwordHash = hash;
    }

    /**
     * Verifies a given password against the stored hash.
     * This is a simplified implementation.
     * @param password the password to verify
     * @return true if password matches hash, false otherwise
     */
    public boolean verifyPassword(String password) {
        // In a real system, use a proper password hashing library.
        // Here we simulate by comparing plain text for demonstration.
        return password != null && password.equals(passwordHash);
    }
}