package com.example;

/**
 * Abstract authentication service for validating users and sessions.
 */
public abstract class AuthenticationService {
    public abstract boolean isAuthenticated(String userId);
    public abstract boolean validateSession(String sessionId);
}