package com.example.agency;

/**
 * Represents an agency operator (actor). 
 * Added to satisfy requirement REQ-003, REQ-004.
 */
public class AgencyOperator {
    private String username;
    private String sessionToken;
    
    public AgencyOperator(String username, String sessionToken) {
        this.username = username;
        this.sessionToken = sessionToken;
    }
    
    public boolean isLoggedIn() {
        // Simplified: assume logged in if session token is not null/empty.
        return sessionToken != null && !sessionToken.isEmpty();
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getSessionToken() {
        return sessionToken;
    }
    
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}