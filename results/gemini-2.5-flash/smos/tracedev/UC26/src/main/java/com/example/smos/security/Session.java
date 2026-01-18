package com.example.smos.security;

import java.util.Date;
import java.util.UUID;

/**
 * Represents an active user session in the system.
 * Corresponds to the 'Session' class in the UML Class Diagram.
 */
public class Session {
    private String sessionId;
    private String userId; // Represents the ID of the user associated with this session
    private Date creationTime;

    /**
     * Constructs a new Session object.
     * @param userId The ID of the user for whom this session is created.
     */
    public Session(String userId) {
        this.sessionId = UUID.randomUUID().toString(); // Generate a unique session ID
        this.userId = userId;
        this.creationTime = new Date(); // Set creation time to now
    }

    // Getters for all attributes
    public String getSessionId() {
        return sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public String toString() {
        return "Session{" +
               "sessionId='" + sessionId + '\'' +
               ", userId='" + userId + '\'' +
               ", creationTime=" + creationTime +
               '}';
    }
}