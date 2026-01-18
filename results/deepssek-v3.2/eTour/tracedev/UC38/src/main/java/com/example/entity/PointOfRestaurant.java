package com.example.entity;

/**
 * Represents a physical point in the restaurant where banners can be displayed.
 * Satisfies architecture requirement REQ-AC-001.
 */
public class PointOfRestaurant {
    private String pointId;
    private String location;
    private int maxBanners;

    public PointOfRestaurant(String pointId, String location, int maxBanners) {
        this.pointId = pointId;
        this.location = location;
        this.maxBanners = maxBanners;
    }

    public String getPointId() {
        return pointId;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    public void setMaxBanners(int count) {
        this.maxBanners = count;
    }
}