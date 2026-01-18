package com.culturalheritage.application.service;

/**
 * Service to authenticate users. Checks if operator is logged in.
 */
public class AuthenticationService {
    public boolean isUserLoggedIn(String operatorId) {
        // Simplified: assume operator is logged in if operatorId is not null/empty
        return operatorId != null && !operatorId.trim().isEmpty();
    }
}