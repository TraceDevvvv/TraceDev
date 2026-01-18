package com.example.model;

/**
 * Represents a Rest Point entity.
 * Implements canAddBanner() enforcing maxBanners limit per REQ-014.
 */
public class RestPoint {
    private String id;
    private String name;
    private String description;
    private int maxBanners;
    private int bannerCount; // Tracks current number of banners.

    public RestPoint(String id, String name, String description, int maxBanners) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxBanners = maxBanners;
        this.bannerCount = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    /**
     * Checks if a banner can be added (REQ-014).
     * Sequence diagram steps 18-20.
     */
    public boolean canAddBanner() {
        return getBannerCount() < maxBanners;
    }

    /**
     * Adds a banner to this rest point.
     * Sequence diagram step 27.
     */
    public OperationResult addBanner(Banner banner) {
        if (canAddBanner()) {
            // In a real system, the banner would be saved via repository.
            bannerCount++;
            return new OperationResult(true, "Banner added successfully.");
        } else {
            return new OperationResult(false, "Cannot add banner: maximum limit reached.");
        }
    }

    public int getBannerCount() {
        return bannerCount;
    }

    /**
     * Checks if the current banner count is below the maximum.
     * Used internally by canAddBanner().
     */
    public boolean checkIfBelowMax(int maxBanners) {
        return bannerCount < maxBanners;
    }
}