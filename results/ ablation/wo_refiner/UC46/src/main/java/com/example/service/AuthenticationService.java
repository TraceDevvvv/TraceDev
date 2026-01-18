package com.example.service;

/**
 * Service responsible for authenticating/verifying tourists.
 * For simplicity, we assume verification always succeeds.
 */
public class AuthenticationService {
    public boolean verifyTourist(String touristId) {
        // In a real system, this would check credentials, tokens, etc.
        // For this example, we assume verification passes.
        return true;
    }
}