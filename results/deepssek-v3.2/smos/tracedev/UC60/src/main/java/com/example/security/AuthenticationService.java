package com.example.security;

/**
 * Interface for authentication service.
 * Satisfies requirement REQ-AUTH-001 (User IS logged in to the system).
 */
public interface AuthenticationService {
    boolean verifyLogin(String userId);
}