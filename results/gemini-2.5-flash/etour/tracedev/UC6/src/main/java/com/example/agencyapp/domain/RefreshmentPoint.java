package com.example.agencyapp.domain;

/**
 * Represents a Refreshment Point in the domain.
 * This is a simple data holder class (POJO).
 */
public class RefreshmentPoint {
    private String id;
    private String name; // Assuming location is a simple String for this context
    private String location; // Assuming location is a simple String for this context

    /**
     * Constructs a new RefreshmentPoint.
     * @param id The unique identifier of the refreshment point.
     * @param name The name of the refreshment point.
     * @param location The location description of the refreshment point.
     */
    public RefreshmentPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    /**
     * Returns the unique identifier of the refreshment point.
     * @return The ID of the refreshment point.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the refreshment point.
     * @return The name of the refreshment point.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the location of the refreshment point.
     * @return The location of the refreshment point.
     */
    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{id='" + id + "', name='" + name + "', location='" + location + "'}";
    }
}