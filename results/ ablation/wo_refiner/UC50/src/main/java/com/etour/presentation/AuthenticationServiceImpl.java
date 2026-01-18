package com.etour.presentation;

/**
 * Simple implementation of AuthenticationService.
 * Assumes authentication is successful if touristId is not null and not empty.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public boolean authenticate(String touristId) {
        // Simplified authentication logic for demonstration
        return touristId != null && !touristId.trim().isEmpty();
    }
}