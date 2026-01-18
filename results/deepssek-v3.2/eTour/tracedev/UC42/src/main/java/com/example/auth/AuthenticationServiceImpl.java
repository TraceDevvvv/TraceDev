package com.example.auth;

import com.example.auth.IUserRepository;

/**
 * Implementation of authentication service.
 * Uses a user repository to validate credentials.
 */
public class AuthenticationServiceImpl implements IAuthenticationService {
    private IUserRepository userRepository;

    public AuthenticationServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isAuthenticated(String userId) {
        // Simplified: assume user is authenticated if userId is not null
        return userId != null && !userId.trim().isEmpty();
    }

    @Override
    public boolean validateCredentials(Credentials credentials) {
        // In a real implementation, would validate against stored credentials
        return true; // Simplified for example
    }
}