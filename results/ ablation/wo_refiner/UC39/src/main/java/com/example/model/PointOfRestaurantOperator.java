package com.example.model;

/**
 * Represents a Point of Restaurant Operator.
 * Added to satisfy requirement REQ-003, REQ-004
 */
public class PointOfRestaurantOperator {
    private String id;
    private String name;
    private String restaurantId;

    public PointOfRestaurantOperator(String id, String name, String restaurantId) {
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * Authenticates the operator using a token.
     * @param token the authentication token
     * @return true if authentication is successful
     */
    public boolean authenticate(String token) {
        // Simplified authentication logic; in a real scenario, validate token with a service.
        return token != null && !token.trim().isEmpty();
    }
}