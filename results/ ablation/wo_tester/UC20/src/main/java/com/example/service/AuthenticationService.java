package com.example.service;

/**
 * Interface for authentication service.
 */
public interface AuthenticationService {
    /**
     * Checks if an operator is logged in.
     */
    boolean isLoggedIn(String operatorId);
}