package com.example.service;

import com.example.model.Session;
import com.example.repository.TokenRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * Secure session management using JWT tokens.
 */
public class JwtSessionService implements SessionService {
    private TokenRepository tokenBlacklist;
    // In-memory store for sessions (assumption: should be replaced with persistent store in production)
    private Map<String, Session> sessionStore = new HashMap<>();

    public JwtSessionService(TokenRepository tokenBlacklist) {
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    public Session getSession(String userId) {
        return sessionStore.get(userId);
    }

    @Override
    public boolean invalidateSession(String userId) {
        Session session = sessionStore.get(userId);
        if (session != null) {
            blacklistToken(session.token);
            clearSessionData(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean isSessionValid(String userId) {
        Session session = sessionStore.get(userId);
        if (session == null) {
            return false;
        }
        // Check if token is blacklisted
        if (tokenBlacklist.isBlacklisted(session.token)) {
            return false;
        }
        return session.isValid();
    }

    /**
     * Blacklists a token to prevent its further use.
     * @param token The token to blacklist.
     */
    public void blacklistToken(String token) {
        tokenBlacklist.addToBlacklist(token);
    }

    /**
     * Clears all session data for the given user ID.
     * @param userId The user ID.
     */
    public void clearSessionData(String userId) {
        sessionStore.remove(userId);
    }

    /**
     * Helper method to add a session (for testing/demo).
     * @param userId The user ID.
     * @param session The session object.
     */
    public void addSession(String userId, Session session) {
        sessionStore.put(userId, session);
    }
}