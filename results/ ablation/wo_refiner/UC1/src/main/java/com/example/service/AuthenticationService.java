package com.example.service;

import com.example.model.AgencyOperator;
import java.util.Map;
import java.util.HashMap;

/**
 * Service for managing user sessions.
 * Added to satisfy requirement REQ-003, REQ-004.
 */
public class AuthenticationService {
    private Map<String, AgencyOperator> activeSessions;

    public AuthenticationService() {
        this.activeSessions = new HashMap<>();
    }

    public boolean validateSession(String sessionId) {
        // Simplified validation: session is valid if it exists in the map
        return activeSessions.containsKey(sessionId);
    }

    public AgencyOperator getCurrentUser() {
        // Simplified: return the first operator in the map, or null if empty.
        // In a real scenario, we would track the current session.
        if (activeSessions.isEmpty()) {
            return null;
        }
        return activeSessions.values().iterator().next();
    }

    public void addSession(String sessionId, AgencyOperator operator) {
        activeSessions.put(sessionId, operator);
    }

    public void removeSession(String sessionId) {
        activeSessions.remove(sessionId);
    }
}