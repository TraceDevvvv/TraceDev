package com.example.domain;

/**
 * PointOfRestaurantOperator class representing an operator for points of restaurant.
 */
public class PointOfRestaurantOperator {
    private String operatorId;
    private String name;

    public PointOfRestaurantOperator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Authenticates the operator (from class diagram).
     */
    public boolean authenticate() {
        return true;
    }
}