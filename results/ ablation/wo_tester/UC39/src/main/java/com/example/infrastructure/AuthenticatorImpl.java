package com.example.infrastructure;

/**
 * Simple implementation of Authenticator.
 */
public class AuthenticatorImpl implements Authenticator {
    @Override
    public boolean isAuthenticated(String userId) {
        // Simplified: assume user is authenticated if userId is not null
        return userId != null && !userId.trim().isEmpty();
    }
}