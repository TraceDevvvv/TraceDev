package com.example.authentication;

import java.util.UUID;

/**
 * Implementation of AuthenticationService using session management.
 */
public class SessionAuthenticationService implements AuthenticationService {
    
    public SessionAuthenticationService() {
        // Constructor
    }
    
    @Override
    public boolean isLoggedIn(UUID userId) {
        // Simplified: In a real application, this would check session state.
        // For this example, we assume the user is always logged in.
        return true;
    }
}