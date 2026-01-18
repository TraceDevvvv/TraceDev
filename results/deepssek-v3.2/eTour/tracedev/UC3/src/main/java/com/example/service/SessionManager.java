package com.example.service;

/**
 * Session-based implementation of AuthenticationService.
 */
public class SessionManager implements AuthenticationService {
    @Override
    public boolean isUserLogged() {
        // Simulated logged-in state
        // In real implementation, check session or token
        return true;
    }
}