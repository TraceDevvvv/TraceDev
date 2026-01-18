package com.example.auth;

import java.util.Date;

/**
 * Manages user session for authentication.
 */
public class Session {
    private String userId;
    private String role;
    private Date loginTime;
    
    public Session(String userId, String role, Date loginTime) {
        this.userId = userId;
        this.role = role;
        this.loginTime = loginTime;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getRole() {
        return role;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }
    
    /**
     * Checks if the session is currently active.
     */
    public boolean isActive() {
        // Simplified: assume session is always active if loginTime is not null
        return loginTime != null;
    }
    
    /**
     * Validates that the session belongs to the given user ID.
     * Used for entry condition: User IS logged in.
     */
    public boolean validateSession(String userId) {
        return isActive() && this.userId.equals(userId);
    }
}