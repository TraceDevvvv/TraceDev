package com.example.entity;

/**
 * PointOfRestaurantOperator entity.
 * Represents an operator user who can manage banners.
 */
public class PointOfRestaurantOperator {
    private String userId;
    private String name;

    public PointOfRestaurantOperator() {}

    public PointOfRestaurantOperator(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the operator is authenticated.
     * In a real implementation, this would check session/token validity.
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        // Simplified implementation
        return userId != null && !userId.trim().isEmpty();
    }
}