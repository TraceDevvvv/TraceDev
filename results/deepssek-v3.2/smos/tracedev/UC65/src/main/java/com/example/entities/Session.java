package com.example.entities;

import java.util.Date;

/**
 * Represents a user session.
 */
public class Session {
    private String sessionId;
    private User user;
    private Date expirationTime;

    public Session(String sessionId, User user, Date expirationTime) {
        this.sessionId = sessionId;
        this.user = user;
        this.expirationTime = expirationTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public User getUser() {
        return user;
    }

    public boolean isValid() {
        return new Date().before(expirationTime);
    }
}