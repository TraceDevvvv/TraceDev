package com.example.etour;

import java.util.Date;

/**
 * Entity class representing a session in the persistence layer.
 */
public class SessionEntity {
    private String sessionId;
    private String userId;
    private Date createdAt;
    private Date expiresAt;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Checks if the session is still valid.
     * @return true if valid.
     */
    public Boolean isValid() {
        Date now = new Date();
        return expiresAt != null && now.before(expiresAt);
    }
}