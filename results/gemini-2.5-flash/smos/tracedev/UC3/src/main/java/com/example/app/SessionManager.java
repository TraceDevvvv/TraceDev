package com.example.app;

/**
 * Manages user sessions, including retrieval and invalidation.
 */
public class SessionManager {

    /**
     * Retrieves the session ID for a given user.
     * In a real application, this would query a session store.
     *
     * @param userId The ID of the user.
     * @return A dummy session ID for the user.
     */
    public String getSessionIdForUser(String userId) {
        System.out.println("SessionManager: Retrieving session ID for user: " + userId);
        // Assumption: For demonstration, returning a dummy session ID.
        // In a real system, this would involve looking up a session in a database or cache.
        String sessionId = "session_" + userId + "_" + System.currentTimeMillis();
        System.out.println("SessionManager: Found session ID: " + sessionId + " for user: " + userId);
        return sessionId;
    }

    /**
     * Invalidates a given session ID, effectively terminating the session.
     *
     * @param sessionId The session ID to invalidate.
     * @return true if the session was successfully terminated, false otherwise.
     */
    public boolean invalidateSession(String sessionId) {
        System.out.println("SessionManager: Invalidating session: " + sessionId);
        // Note right of SessionMgr: Securely terminates the session
        // Assumption: For demonstration, always returning true, simulating successful termination.
        // In a real system, this would involve removing the session from the session store,
        // potentially clearing cookies, etc.
        System.out.println("SessionManager: Session " + sessionId + " securely terminated."); // m8: Securely terminates the session
        return true;
    }
}