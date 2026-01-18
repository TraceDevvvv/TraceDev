package com.example.convention.model;

/**
 * Represents a Refreshment Point in the system.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private boolean isDesignated;

    /**
     * Constructor for RefreshmentPoint.
     *
     * @param id The unique identifier of the refreshment point.
     * @param name The name of the refreshment point.
     * @param isDesignated True if it's a designated point of rest, false otherwise.
     */
    public RefreshmentPoint(String id, String name, boolean isDesignated) {
        this.id = id;
        this.name = name;
        this.isDesignated = isDesignated;
    }

    /**
     * Checks if this refreshment point is a designated point of rest.
     * [R3] Method added to satisfy requirement R3.
     *
     * @return True if designated, false otherwise.
     */
    public boolean isDesignatedPointOfRest() {
        return isDesignated;
    }

    // Getters for attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsDesignated() {
        return isDesignated;
    }
}