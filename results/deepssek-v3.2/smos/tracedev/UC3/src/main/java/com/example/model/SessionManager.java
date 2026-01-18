package com.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages user sessions, including creation, retrieval, and invalidation.
 */
public class SessionManager {
    private Map<String, Session> activeSessions = new HashMap<>();
    private SecurityManager securityManager = new SecurityManager();
    private AuditLogger auditLogger = new AuditLogger();

    /**
     * Retrieves a session by its token.
     * @param token the session token.
     * @return the Session object, or null if not found.
     */
    public Session getSession(String token) {
        return activeSessions.get(token);
    }

    /**
     * Invalidates a session securely.
     * @param token the session token to invalidate.
     * @return true if invalidation was successful, false otherwise.
     */
    public boolean invalidateSession(String token) {
        System.out.println("SessionManager invalidating session for token: " + token);
        // Use SecurityManager for secure invalidation as per sequence diagram
        boolean success = securityManager.secureInvalidate(token);
        if (success) {
            // Log the logout action as per sequence diagram
            Session session = activeSessions.get(token);
            if (session != null) {
                auditLogger.logAction(session.userId, "logout", System.currentTimeMillis());
            }
            clearSessionData(); // Clear any remaining session data
            System.out.println("SessionManager: session invalidated successfully.");
        } else {
            System.out.println("SessionManager: secure invalidation failed.");
        }
        return success;
    }

    /**
     * Clears session data from the manager.
     */
    public void clearSessionData() {
        // In a real system, this might remove expired sessions
        System.out.println("SessionManager clearing session data.");
    }
}