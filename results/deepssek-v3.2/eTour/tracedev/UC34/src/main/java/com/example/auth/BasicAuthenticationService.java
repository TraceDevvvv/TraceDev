package com.example.auth;

/**
 * Basic implementation of AuthenticationService.
 * Added to satisfy requirement Entry Conditions: Guest User IS logged on
 */
public class BasicAuthenticationService implements AuthenticationService {
    @Override
    public boolean authenticate(String username, String password) {
        // Simulate authentication logic
        return username != null && password != null && username.equals("guest") && password.equals("guest123");
    }

    @Override
    public boolean isUserLoggedIn(String userId) {
        // For simplicity, assume user is logged in if userId is not null.
        // In a real system, you would check a session or token.
        return userId != null && !userId.isEmpty();
    }
}