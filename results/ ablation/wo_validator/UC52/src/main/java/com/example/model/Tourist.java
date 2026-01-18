package com.example.model;

/**
 * Represents a Tourist user.
 */
public class Tourist {
    private String userId;

    public Tourist(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}