package com.example.domain;

/**
 * Represents a password with hash and salt.
 */
public class Password {
    private String hash;
    private String salt;

    public Password(String hash, String salt) {
        this.hash = hash;
        this.salt = salt;
    }

    /**
     * Verifies an input password against the stored hash and salt.
     * This method is a placeholder; actual verification is done by PasswordEncoder.
     */
    public boolean verify(String inputPassword) {
        // Actual verification would require a PasswordEncoder; this is a stub.
        // Assuming verification is done elsewhere (e.g., PasswordEncoder).
        return false;
    }

    /**
     * Updates the password with a new hash and salt.
     * This method is a placeholder; actual update is done by PasswordEncoder.
     */
    public void update(String newPassword) {
        // Actual update would require a PasswordEncoder; this is a stub.
        // The real update is performed by ChangePasswordService using PasswordEncoder.
        // For traceability, we implement a minimal placeholder that does nothing.
        // Business logic is in service layer.
    }

    // Getters and setters
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}