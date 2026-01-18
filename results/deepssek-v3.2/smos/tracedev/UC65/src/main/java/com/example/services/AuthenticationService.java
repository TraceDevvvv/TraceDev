package com.example.serv;

import com.example.entities.Session;
import com.example.entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

/**
 * Service for authentication and session management.
 */
public class AuthenticationService {
    // In-memory storage for sessions (simplified for example)
    private Map<String, Session> sessionStore = new HashMap<>();

    public Session login(String username, String password) {
        // Simplified authentication - in a real system, validate credentials
        User user = new User("user123", username, "DIRECTION");
        Date expiration = new Date(System.currentTimeMillis() + 3600000); // 1 hour
        Session session = new Session("session" + System.currentTimeMillis(), user, expiration);
        sessionStore.put(session.getSessionId(), session);
        return session;
    }

    public void logout(Session session) {
        sessionStore.remove(session.getSessionId());
    }

    public Session validateSession(String sessionId) {
        Session session = sessionStore.get(sessionId);
        if (session != null && session.isValid()) {
            return session;
        }
        return null;
    }
}