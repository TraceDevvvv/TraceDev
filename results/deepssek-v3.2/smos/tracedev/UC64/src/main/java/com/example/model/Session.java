package com.example.model;

import java.util.Date;

public class Session {
    private String userId;
    private String userRole;
    private Date loginTime;

    public Session(String userId, String userRole, Date loginTime) {
        this.userId = userId;
        this.userRole = userRole;
        this.loginTime = loginTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * Determines if the session is still valid.
     * Assumption: session expires after 30 minutes.
     */
    public boolean isValid() {
        long now = System.currentTimeMillis();
        long login = loginTime.getTime();
        long thirtyMinutes = 30 * 60 * 1000;
        return (now - login) < thirtyMinutes;
    }
}