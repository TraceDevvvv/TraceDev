package com.example.domain;

/**
 * Domain entity representing a Point of Restaurant.
 */
public class PointOfRestaurant {
    private final String id;
    private final String name;

    public PointOfRestaurant(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}