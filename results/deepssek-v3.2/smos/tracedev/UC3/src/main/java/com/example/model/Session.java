package com.example.model;

/**
 * Represents a user session.
 */
public class Session {
    public String sessionId;
    public String userId;
    public long creationTime;
    public long lastAccessedTime;

    /**
     * Checks if the session is currently valid.
     * @return true if the session is valid.
     */
    public boolean isValid() {
        // Simplified: session is valid if it's not too old
        long currentTime = System.currentTimeMillis();
        long sessionAge = currentTime - lastAccessedTime;
        return sessionAge < 30 * 60 * 1000; // 30 minutes expiry
    }

    /**
     * Invalidates the session.
     */
    public void invalidate() {
        System.out.println("Session " + sessionId + " invalidated.");
        // In a real system, this would mark the session as invalid
        lastAccessedTime = 0;
    }

    /**
     * Getter for sessionId.
     * @return the session ID.
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Setter for sessionId.
     * @param sessionId the session ID to set.
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}