package com.example.session;

import com.example.agency.AgencyOperator;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages user sessions.
 * Added to satisfy requirement REQ-004.
 */
public class SessionManager {
    private Map<String, AgencyOperator> activeSessions = new HashMap<>();
    
    /**
     * Validates a session token.
     * @param sessionToken the token to validate
     * @return true if valid, false otherwise
     */
    public boolean validateSession(String sessionToken) {
        return activeSessions.containsKey(sessionToken);
    }
    
    /**
     * Retrieves the current user based on session token.
     * @return the AgencyOperator if session is valid, null otherwise.
     */
    public AgencyOperator getCurrentUser() {
        // Simplified: assume we have a current session token context.
        // In a real app, we would retrieve token from request context.
        // For simulation, return the first operator if any.
        if (!activeSessions.isEmpty()) {
            return activeSessions.values().iterator().next();
        }
        return null;
    }
    
    /**
     * Adds a session (for simulation).
     * @param sessionToken the session token
     * @param operator the agency operator
     */
    public void addSession(String sessionToken, AgencyOperator operator) {
        activeSessions.put(sessionToken, operator);
    }
    
    /**
     * Removes a session (for simulation).
     * @param sessionToken the session token
     */
    public void removeSession(String sessionToken) {
        activeSessions.remove(sessionToken);
    }
}