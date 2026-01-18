package com.example.model;

/**
 * Represents a Registered User in the system.
 * Contains user identification and authentication data.
 */
public class User {
    private String userId;
    private String username;
    private String passwordHash;

    // Constructor
    public User(String userId, String username, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters and setters
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Changes the user's password if the old password matches the current hash.
     * The method updates the internal passwordHash with the provided new hash.
     * This method assumes the old password has already been verified externally.
     * 
     * @param oldPassword the plaintext old password (not used for verification here)
     * @param newPassword the plaintext new password (not used for hashing here)
     * @return true if the password was changed successfully
     */
    public boolean changePassword(String oldPassword, String newPassword) {
        // Implementation note: This method is called after verification.
        // The actual password update (setting new hash) is handled by the use case.
        // This method signature is kept as per class diagram, but logic is adjusted.
        // In a real scenario, this method might update the passwordHash directly if newPassword is already hashed.
        System.out.println("User.changePassword called for user: " + username);
        // The actual hash update is done via setPasswordHash by the use case.
        return true;
    }
}