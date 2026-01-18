package com.example.controller;

import com.example.model.Session;
import java.util.Date;

public class LoginController {

    /**
     * Authenticates a user and returns a session.
     * As per sequence diagram entry condition.
     */
    public Session authenticate(String userId, String password) {
        // Simple mock authentication: accept any non empty password.
        if (userId == null || userId.trim().isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        // Assume authentication succeeds and create a session.
        // Role is determined based on userId prefix for demonstration.
        String role = userId.startsWith("DIR") ? "DIRECTION" : "TEACHER";
        return new Session(userId, role, new Date());
    }
}