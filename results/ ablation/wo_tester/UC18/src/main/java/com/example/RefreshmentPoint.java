package com.example;

/**
 * Represents a refreshment point that can have banners.
 * Contains logic to check if a new banner can be added.
 */
public class RefreshmentPoint {
    private String id;
    private String conventionId;
    private int maxBanners;
    private int currentBanners;

    /**
     * Constructor for RefreshmentPoint.
     * @param id The unique identifier of the refreshment point.
     * @param conventionId The ID of the convention this point belongs to.
     * @param maxBanners The maximum number of banners allowed at this point.
     */
    public RefreshmentPoint(String id, String conventionId, int maxBanners) {
        this.id = id;
        this.conventionId = conventionId;
        this.maxBanners = maxBanners;
        this.currentBanners = 0; // Initialized to zero by default
    }

    public String getId() {
        return id;
    }

    public String getConventionId() {
        return conventionId;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    public int getCurrentBanners() {
        return currentBanners;
    }

    /**
     * Updates the current number of banners.
     * @param count The new count of banners.
     */
    public void setCurrentBanners(int count) {
        this.currentBanners = count;
    }

    /**
     * Determines if a new banner can be added.
     * @return true if current banners is less than max banners, false otherwise.
     */
    public boolean canAddBanner() {
        return currentBanners < maxBanners;
    }
}