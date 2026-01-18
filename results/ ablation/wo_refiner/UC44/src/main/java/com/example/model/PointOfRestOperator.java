package com.example.model;

/**
 * Represents a restaurant point operator (user).
 */
public class PointOfRestOperator {
    private int userId;
    private String username;
    private int pointOfRestId;

    // Constructor
    public PointOfRestOperator(int userId, String username, int pointOfRestId) {
        this.userId = userId;
        this.username = username;
        this.pointOfRestId = pointOfRestId;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public int getPointOfRestId() {
        return pointOfRestId;
    }

    public String getUsername() {
        return username;
    }
}