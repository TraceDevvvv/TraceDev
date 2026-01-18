package com.example.service;

/**
 * Handles user authentication and session validation.
 */
public class AuthenticationService {
    private SecurityManager securityManager;

    public AuthenticationService(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public boolean authenticate(String userId) {
        // Simplified authentication - always returns true for demo
        // In reality, would check credentials against database
        String token = securityManager.createSession(userId);
        return token != null;
    }

    public boolean validateSession(String token) {
        return securityManager.validateSession(token);
    }
}