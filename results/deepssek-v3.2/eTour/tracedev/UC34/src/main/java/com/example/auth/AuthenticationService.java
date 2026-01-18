package com.example.auth;

/**
 * Authentication Service interface.
 */
public interface AuthenticationService {
    boolean authenticate(String username, String password);
    boolean isUserLoggedIn(String userId);
}