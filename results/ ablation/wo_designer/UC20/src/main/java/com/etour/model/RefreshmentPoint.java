package com.etour.model;

/**
 * Entity representing a Refreshment Point.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String location;
    private int maxBanners;

    public RefreshmentPoint() {}

    public RefreshmentPoint(int id, String name, String location, int maxBanners) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.maxBanners = maxBanners;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getMaxBanners() {
        return maxBanners;
    }

    public void setMaxBanners(int maxBanners) {
        this.maxBanners = maxBanners;
    }
}