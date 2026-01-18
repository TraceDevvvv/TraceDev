package com.example.port;

/**
 * Port interface for handling authentication failures.
 * Part of the hexagonal architecture.
 */
public interface AuthenticationFailurePort {
    /**
     * Called when authentication fails.
     */
    void onFailure();
}