package com.example.etour;

/**
 * Secure authentication service interface.
 */
public interface AuthService {
    AuthResultDTO authenticate(LoginDTO loginData);
    String generateSessionToken(String userId);
    void invalidateSession(String userId);
}