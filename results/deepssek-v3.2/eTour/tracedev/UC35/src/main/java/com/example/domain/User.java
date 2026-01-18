package com.example.domain;

/**
 * Domain entity representing a system user.
 */
public class User {
    private String userId;
    private String username;
    private String passwordHash;
    private Role role;

    public User(String userId, String username, String passwordHash, Role role) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public boolean validatePassword(String inputPassword, String encodedPassword) {
        // Use the encoded password to verify the input password.
        return encodedPassword.equals(passwordHash);
    }
}