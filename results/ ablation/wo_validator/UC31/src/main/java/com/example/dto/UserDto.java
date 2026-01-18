package com.example.dto;

/**
 * Data Transfer Object for user data.
 */
public class UserDto {
    private String userId;
    private String username;
    private String passwordHash;

    // Constructors
    public UserDto() {
    }

    public UserDto(String userId, String username, String passwordHash) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    // Setters (if needed)
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}