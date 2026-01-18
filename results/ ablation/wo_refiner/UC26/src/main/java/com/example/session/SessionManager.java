package com.example.session;

import java.time.LocalDateTime;

/**
 * Manages user session and authentication.
 */
public class SessionManager {
    private int userId;
    private boolean isAuthenticated;
    private LocalDateTime lastActivity;

    public SessionManager(int userId) {
        this.userId = userId;
        this.isAuthenticated = true;
        this.lastActivity = LocalDateTime.now();
    }

    public boolean isLoggedIn() {
        return isAuthenticated && validateSession();
    }

    public boolean validateSession() {
        // Simplified validation: session is valid if authenticated and activity within last 30 minutes
        if (!isAuthenticated) return false;
        LocalDateTime thirtyMinutesAgo = LocalDateTime.now().minusMinutes(30);
        boolean valid = lastActivity.isAfter(thirtyMinutesAgo);
        if (valid) {
            lastActivity = LocalDateTime.now();
        }
        return valid;
    }

    public void logout() {
        isAuthenticated = false;
    }

    public int getUserId() {
        return userId;
    }
}