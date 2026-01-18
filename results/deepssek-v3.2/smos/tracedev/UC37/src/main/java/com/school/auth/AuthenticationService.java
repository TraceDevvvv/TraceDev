package com.school.auth;

/**
 * Service for authentication and session management.
 */
public class AuthenticationService {
    public Session login(String username, String password) {
        // Simulate login
        String token = "session-" + username + "-" + System.currentTimeMillis();
        Administrator admin = new Administrator(username);
        return new Session(token, admin);
    }

    public boolean validateSession(String token) {
        // For simplicity, assume token is valid if not null and starts with "session-"
        return token != null && token.startsWith("session-");
    }

    public void logout(String token) {
        System.out.println("Logged out session: " + token);
    }
}