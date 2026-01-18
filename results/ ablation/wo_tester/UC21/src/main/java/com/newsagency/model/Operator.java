package com.newsagency.model;

import java.util.Date;
import java.util.Map;

/**
 * Represents an operator with authentication capabilities.
 * Used by AgencyOperator to authenticate.
 */
public class Operator {
    private boolean isAuthenticated = false;
    private Date lastLoginTime;
    
    public Operator() {
    }
    
    /**
     * Authenticates an operator using provided credentials.
     * @param credentials Map containing authentication credentials
     * @return true if authentication successful, false otherwise
     */
    public boolean authenticate(Map<String, String> credentials) {
        // Simplified authentication logic
        if (credentials != null && credentials.containsKey("operatorId")) {
            String operatorId = credentials.get("operatorId");
            if (operatorId != null && !operatorId.trim().isEmpty()) {
                isAuthenticated = true;
                lastLoginTime = new Date();
                return true;
            }
        }
        isAuthenticated = false;
        return false;
    }
    
    public boolean isLoggedIn() {
        return isAuthenticated;
    }
    
    public void logout() {
        isAuthenticated = false;
        lastLoginTime = null;
    }
    
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}