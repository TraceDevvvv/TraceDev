package com.example.manager;

import com.example.entity.User;
import com.example.entity.Session;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages user sessions.
 * Ensures a user is not already logged in (Entry Condition).
 */
public class SessionManager {
    private Map<String, Session> activeSessions = new HashMap<>();

    /**
     * Checks if a user is already logged in.
     */
    public boolean isUserLoggedIn(String username) {
        Session session = activeSessions.get(username);
        return session != null && session.isValid();
    }

    /**
     * Creates a new session for the given user.
     * Assumes the user is not already logged in (checked earlier).
     */
    public Session createSession(User user) {
        String sessionId = UUID.randomUUID().toString();
        LocalDateTime expiryTime = LocalDateTime.now().plusHours(1); // 1 hour validity
        Session session = new Session(sessionId, user.getUsername(), expiryTime);
        activeSessions.put(user.getUsername(), session);
        return session;
    }
}