package com.system.authentication;

import com.system.entities.User;
import java.util.Map;

/**
 * Service responsible for user authentication and session management.
 */
public class AuthenticationService {
    private Map<String, Session> sessionStore;
    private User currentUser; // In a real application, this would be fetched based on session.

    public AuthenticationService(Map<String, Session> sessionStore, User currentUser) {
        this.sessionStore = sessionStore;
        this.currentUser = currentUser;
    }

    public Session authenticate(String username, String password) {
        // Simplified authentication - in real scenario, check credentials and create session.
        String sessionId = "session_" + username;
        Session session = new Session(sessionId, "user123", java.time.LocalDateTime.now(), java.time.LocalDateTime.now().plusHours(1));
        sessionStore.put(sessionId, session);
        return session;
    }

    public boolean validateSession(String sessionId) {
        Session session = sessionStore.get(sessionId);
        return session != null && session.isValid();
    }

    public User getCurrentUser() {
        return currentUser;
    }
}