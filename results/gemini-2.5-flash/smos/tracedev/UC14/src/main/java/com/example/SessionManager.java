package com.example;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages user sessions.
 * Added to satisfy requirement R3 (entry condition).
 */
public class SessionManager {
    // In-memory store for active sessions
    private Map<String, String> activeSessions = new HashMap<>(); // sessionId -> userId

    /**
     * Creates a new session for a given user ID.
     *
     * @param userId The ID of the user for whom to create a session.
     * @return A unique session ID.
     */
    public String createSession(String userId) {
        String sessionId = UUID.randomUUID().toString();
        activeSessions.put(sessionId, userId);
        System.out.println("SessionManager: Created session " + sessionId + " for user " + userId);
        return sessionId;
    }

    /**
     * Checks if a given session ID is valid and active.
     *
     * @param sessionId The session ID to validate.
     * @return true if the session is valid, false otherwise.
     */
    public boolean isValidSession(String sessionId) {
        boolean isValid = activeSessions.containsKey(sessionId);
        System.out.println("SessionManager: Session " + sessionId + " is " + (isValid ? "valid" : "invalid"));
        return isValid;
    }
}