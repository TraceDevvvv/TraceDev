package com.banner.auth;

/**
 * Authenticates users based on credentials.
 */
public class Authenticator {
    public Authenticator() {}

    /**
     * Validate credentials (simplified).
     */
    public boolean validate(Credentials credentials) {
        return credentials != null &&
               credentials.getUserId() != null &&
               credentials.getToken() != null &&
               !credentials.getUserId().isEmpty() &&
               !credentials.getToken().isEmpty();
    }

    /**
     * Check if a user is currently authenticated.
     */
    public boolean isAuthenticated(String userId) {
        // Simplified: assume user with non‑null userId is authenticated.
        return userId != null && !userId.isEmpty();
    }
}