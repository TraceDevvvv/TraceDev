package com.example.entity;

import java.util.Objects;

/**
 * Core Domain Entity representing a user.
 */
public class User {
    private String username;
    private String passwordHash;

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) {
        return Objects.equals(passwordHash, password);
    }
}