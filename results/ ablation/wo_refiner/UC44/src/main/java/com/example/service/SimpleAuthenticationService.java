package com.example.service;

import com.example.model.PointOfRestOperator;

/**
 * Simple implementation of AuthenticationService for demonstration.
 * Assumption: In real scenario, would integrate with actual auth system.
 */
public class SimpleAuthenticationService implements AuthenticationService {
    private PointOfRestOperator currentOperator;

    public SimpleAuthenticationService(PointOfRestOperator operator) {
        this.currentOperator = operator;
    }

    @Override
    public boolean isAuthenticated(int userId) {
        // Simplified authentication check
        return currentOperator != null && currentOperator.getUserId() == userId;
    }

    @Override
    public PointOfRestOperator getCurrentUser() {
        return currentOperator;
    }
}