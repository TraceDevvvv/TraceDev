package com.example.etour.entities;

/**
 * Entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;

    public RefreshmentPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    /**
     * Marks this refreshment point for deletion.
     * The actual deletion is performed by the repository.
     * @return true if the object is valid for deletion, false otherwise.
     */
    public boolean delete() {
        // In a real scenario, this might change the state of the object.
        // For simplicity, we assume it always returns true.
        return true;
    }
}