package com.example.banner.domain;

/**
 * Domain entity representing a Refreshment Point.
 * Manages banner count and limit verification.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private int currentBannerCount;
    private int maxBannerCount;

    /**
     * Constructor for RefreshmentPoint.
     * @param id unique identifier
     * @param name name of the refreshment point
     * @param maxBannerCount maximum banners allowed
     */
    public RefreshmentPoint(String id, String name, int maxBannerCount) {
        this.id = id;
        this.name = name;
        this.maxBannerCount = maxBannerCount;
        this.currentBannerCount = 0; // initial count is zero
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    public int getMaxBannerCount() {
        return maxBannerCount;
    }

    /**
     * Increments the current banner count by one.
     */
    public void incrementBannerCount() {
        this.currentBannerCount++;
    }

    /**
     * Checks if the refreshment point can accept a new banner.
     * @return true if current count is less than max, false otherwise
     */
    public boolean canAcceptBanner() {
        return currentBannerCount < maxBannerCount;
    }

    /**
     * Verifies the banner limit.
     * @return true if limit not exceeded, false otherwise
     */
    public boolean verifyBannerLimit() {
        return canAcceptBanner();
    }
}