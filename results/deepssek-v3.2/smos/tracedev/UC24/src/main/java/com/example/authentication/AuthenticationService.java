package com.example.authentication;

/**
 * Service for authentication and session validation.
 */
public class AuthenticationService {
    public boolean validateSession(String userId) {
        // In a real application, validate the session token or user ID.
        // For simplicity, return true if userId is not null.
        return userId != null && !userId.trim().isEmpty();
    }
}