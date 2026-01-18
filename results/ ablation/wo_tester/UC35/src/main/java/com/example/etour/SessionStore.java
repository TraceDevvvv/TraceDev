package com.example.etour;

import java.util.Date;

/**
 * Database for storing session data.
 */
public class SessionStore {
    
    /**
     * Stores a session.
     * @param sessionId the session ID.
     * @param userId the user ID.
     * @param expiry the expiry date.
     */
    public void storeSession(String sessionId, String userId, Date expiry) {
        // In a real implementation, this would store in a database.
        System.out.println("Session stored: " + sessionId + " for user: " + userId + " expires: " + expiry);
    }
    
    /**
     * Deletes session by user ID.
     * @param userId the user ID.
     */
    public void deleteByUserId(String userId) {
        System.out.println("Session deleted from SessionStore for user: " + userId);
    }
}