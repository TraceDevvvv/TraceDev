package com.example.domain;

/**
 * Represents the current authenticated user context.
 */
public class UserContext {
    private final String userId;
    private final String role;
    private final String restaurantId;

    public UserContext(String userId, String role, String restaurantId) {
        this.userId = userId;
        this.role = role;
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRole() {
        return role;
    }
}