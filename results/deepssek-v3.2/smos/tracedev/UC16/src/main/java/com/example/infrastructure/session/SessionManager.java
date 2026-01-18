package com.example.infrastructure.session;

import com.example.domain.model.User;
import java.util.Map;
import java.util.HashMap;

/**
 * Manages user sessions.
 */
public class SessionManager {
    private Map<String, Session> activeSessions = new HashMap<>();

    /**
     * Checks if a user is logged in.
     * Consistent with sequence diagram parameter name.
     */
    public boolean isUserLoggedIn(String userId) {
        return activeSessions.containsKey(userId);
    }

    /**
     * Returns the current logged-in user.
     * Simplified: returns a dummy user.
     */
    public User getCurrentUser() {
        return new User("admin", "Administrator");
    }

    // Inner class representing a session
    private static class Session {
        private String userId;
        // other session details
    }
}