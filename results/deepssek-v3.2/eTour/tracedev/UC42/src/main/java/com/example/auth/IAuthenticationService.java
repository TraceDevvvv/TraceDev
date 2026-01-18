package com.example.auth;

import com.example.auth.Credentials;

/**
 * Service for authentication operations.
 */
public interface IAuthenticationService {
    boolean isAuthenticated(String userId);
    boolean validateCredentials(Credentials credentials);
}