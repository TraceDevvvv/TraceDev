package com.example.pointofrest;

/**
 * Represents an Agency Operator user.
 * Added to satisfy requirement REQ-001 (Entry Conditions: Agency IS logged in.)
 */
public class AgencyOperator {
    public String userId;
    public String username;

    public AgencyOperator(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public String toString() {
        return "AgencyOperator{" +
               "userId='" + userId + '\'' +
               ", username='" + username + '\'' +
               '}';
    }
}