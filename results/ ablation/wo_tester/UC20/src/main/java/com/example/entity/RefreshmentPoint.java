package com.example.entity;

/**
 * Represents a refreshment point where banners can be displayed.
 */
public class RefreshmentPoint {
    private String pointId;
    private String name;
    private String location;
    private int currentBannerCount;
    private int maxBannerLimit;

    public RefreshmentPoint(String pointId, String name, String location, int maxBannerLimit) {
        this.pointId = pointId;
        this.name = name;
        this.location = location;
        this.maxBannerLimit = maxBannerLimit;
        this.currentBannerCount = 0;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
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

    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    public void setCurrentBannerCount(int currentBannerCount) {
        this.currentBannerCount = currentBannerCount;
    }

    public int getMaxBannerLimit() {
        return maxBannerLimit;
    }

    public void setMaxBannerLimit(int maxBannerLimit) {
        this.maxBannerLimit = maxBannerLimit;
    }

    /**
     * Checks if the refreshment point has reached its maximum banner limit.
     */
    public boolean hasReachedMaxBannerLimit() {
        return currentBannerCount >= maxBannerLimit;
    }

    /**
     * Increments the banner count by one.
     */
    public void incrementBannerCount() {
        if (currentBannerCount < maxBannerLimit) {
            currentBannerCount++;
            System.out.println("Incremented banner count for point " + pointId + " to " + currentBannerCount);
        }
    }

    /**
     * Checks if the refreshment point can accept more banners.
     */
    public boolean canAcceptMoreBanners() {
        return currentBannerCount < maxBannerLimit;
    }
}