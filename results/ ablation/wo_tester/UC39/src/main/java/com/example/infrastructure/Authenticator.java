package com.example.infrastructure;

/**
 * Authentication interface.
 * As per R-AUTH.
 */
public interface Authenticator {
    boolean isAuthenticated(String userId);
}