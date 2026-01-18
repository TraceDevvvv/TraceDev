package com.example.bannerapp;

/**
 * Represents a PointOfRest entity.
 */
public class PointOfRest {
    private String id;
    private String name;

    /**
     * Constructs a new PointOfRest.
     * @param id The unique identifier for the Point of Rest.
     * @param name The name of the Point of Rest.
     */
    public PointOfRest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the Point of Rest.
     * @return The Point of Rest's ID.
     */
    public String getId() {
        return id;
    }

    // Assuming other getters might be needed, adding for completeness.
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PointOfRest{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}