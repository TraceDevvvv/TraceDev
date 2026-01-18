package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a login request with user credentials and timestamp.
 */
public class LoginRequest {
    public String username;
    public String password;
    public LocalDateTime timestamp;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Validates the login request (basic non‑null and non‑empty check).
     * @return true if username and password are not empty
     */
    public boolean validate() {
        return username != null && !username.trim().isEmpty() &&
               password != null && !password.trim().isEmpty();
    }
}