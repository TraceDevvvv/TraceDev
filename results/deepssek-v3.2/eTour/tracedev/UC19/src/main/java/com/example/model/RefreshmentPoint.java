package com.example.model;

import java.util.List;

/**
 * Represents a refreshment point that contains banners.
 */
public class RefreshmentPoint {
    private long id;
    private String name;
    private String location;

    public RefreshmentPoint() {
    }

    public RefreshmentPoint(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Retrieves banners associated with this refreshment point.
     * This is a placeholder; actual implementation would depend on data access.
     * @return list of banners (could be empty)
     */
    public List<Banner> getBanners() {
        // In a real implementation, this would fetch banners from a repository.
        // For simulation, return an empty list.
        return List.of();
    }
}