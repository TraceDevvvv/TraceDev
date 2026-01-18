/**
 * This class represents a "point of rest" or refreshment point in the system.
 * It holds basic information like an ID, name, and location.
 */
package com.chatdev.model;
/**
 * This class represents a "point of rest" or refreshment point in the system.
 * It holds basic information like an ID, name, and location.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    /**
     * Constructs a new RefreshmentPoint.
     * @param id The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param location The geographical location description of the refreshment point.
     */
    public RefreshmentPoint(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
    /**
     * @return The ID of the refreshment point.
     */
    public int getId() {
        return id;
    }
    /**
     * @return The name of the refreshment point.
     */
    public String getName() {
        return name;
    }
    /**
     * @return The location of the refreshment point.
     */
    public String getLocation() {
        return location;
    }
    /**
     * Returns a string representation of the RefreshmentPoint, useful for display in UI lists.
     * @return A formatted string containing the ID, name, and location.
     */
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Location: %s", id, name, location);
    }
}