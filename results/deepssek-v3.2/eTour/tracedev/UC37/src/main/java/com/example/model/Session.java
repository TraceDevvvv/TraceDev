package com.example.model;

import java.util.Date;

/**
 * Represents a user session with an expiration time.
 */
public class Session {
    public String userId;
    public String token;
    public Date expirationTime;

    public Session(String userId, String token, Date expirationTime) {
        this.userId = userId;
        this.token = token;
        this.expirationTime = expirationTime;
    }

    /**
     * Checks if the session is still valid based on expiration time.
     * @return true if the session is valid, false otherwise.
     */
    public boolean isValid() {
        Date now = new Date();
        return expirationTime.after(now);
    }

    /**
     * Invalidates the session by setting expiration to the past.
     */
    public void invalidate() {
        this.expirationTime = new Date(System.currentTimeMillis() - 1000);
    }
}