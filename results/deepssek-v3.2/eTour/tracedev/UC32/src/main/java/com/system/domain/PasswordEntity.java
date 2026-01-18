package com.system.domain;

/**
 * Entity representing user password data and operations.
 */
public class PasswordEntity {
    private String userId;
    private String passwordHash;

    public PasswordEntity(String userId, String passwordHash) {
        this.userId = userId;
        this.passwordHash = passwordHash;
    }

    public String getUserId() {
        return userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Hashes a plain text password (simulated).
     */
    public String hashPassword(String plain) {
        // In a real system, use a secure hashing algorithm like BCrypt
        return "HASHED_" + plain;
    }

    /**
     * Verifies if the provided plain password matches the stored hash.
     */
    public boolean verifyPassword(String plain) {
        return passwordHash.equals(hashPassword(plain));
    }

    /**
     * Updates the password hash.
     */
    public void updatePassword(String newHash) {
        this.passwordHash = newHash;
    }
}