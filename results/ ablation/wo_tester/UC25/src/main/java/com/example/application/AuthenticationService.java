package com.example.application;

/**
 * Interface for authentication checks.
 * Added to satisfy requirement Entry Conditions.
 */
public interface AuthenticationService {
    boolean isUserLoggedIn(String userId);
}