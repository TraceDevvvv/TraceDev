package com.example.domain;

/**
 * Domain entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String description;

    public RefreshmentPoint(String id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    /**
     * Marks the refreshment point as deleted in the domain (logical deletion).
     * This method is called by the repository before physical database deletion.
     */
    public void delete() {
        // In a real scenario, we might set a deleted flag or perform domain cleanup.
        // For simplicity, we assume the repository handles physical deletion.
    }
}