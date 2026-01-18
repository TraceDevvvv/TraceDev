package com.example.statistics;

/**
 * Domain Layer Entity
 * Represents a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;

    /**
     * Constructor for RefreshmentPoint.
     * @param id The unique ID of the refreshment point.
     * @param name The name of the refreshment point.
     */
    public RefreshmentPoint(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Standard setters could be added if mutable.
}