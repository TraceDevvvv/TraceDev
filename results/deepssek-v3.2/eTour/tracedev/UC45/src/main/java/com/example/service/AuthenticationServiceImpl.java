package com.example.service;

/**
 * Simple implementation of AuthenticationService.
 * In a real system, this would integrate with an authentication provider.
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public boolean isAuthenticated(String operatorId) {
        // Simplified: assume all non‑null, non‑empty IDs are authenticated.
        return operatorId != null && !operatorId.trim().isEmpty();
    }
}