package com.example.service;

/**
 * Interface for authentication operations.
 */
public interface AuthenticationService {

    /**
     * Checks if the operator is authenticated.
     */
    boolean isAuthenticated(String operatorId);
}