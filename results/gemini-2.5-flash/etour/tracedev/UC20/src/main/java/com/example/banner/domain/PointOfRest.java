
package com.example.banner.domain;

import java.util.UUID;

/**
 * Represents a Point of Rest (e.g., a refreshment stand, a tourist spot)
 * where banners can be displayed.
 * Each PointOfRest has a maximum capacity for banners.
 */
public class PointOfRest {
    /** Unique identifier for the point of rest. */
    public String id;
    /** Name of the point of rest. */
    public String name; // CD: +name: String
    /** Maximum number of banners that can be displayed at this point of rest. */
    public int maxBanners; // CD: +maxBanners: int
    /** Current number of banners displayed at this point of rest. */
    public int currentBannerCount; // CD: +currentBannerCount: int

    /**
     * Constructor for PointOfRest.
     *
     * @param name The name of the point of rest.
     * @param maxBanners The maximum number of banners allowed.
     */
    public PointOfRest(String name, int maxBanners) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.maxBanners = maxBanners;
        this.currentBannerCount = 0; // Initially no banners
    }

    /**
     * Checks if a new banner can be added to this point of rest.
     *
     * @return True if `currentBannerCount` is less than `maxBanners`, false otherwise.
     */
    public boolean canAddBanner() { // CD: +canAddBanner(): boolean; SD: m36
        return currentBannerCount < maxBanners;
    }

    /**
     * Increments the current banner count.
     * Assumes `canAddBanner()` was checked before calling this.
     */
    public void incrementBannerCount() { // CD: +incrementBannerCount(): void
        currentBannerCount++;
    }

    /**
     * Decrements the current banner count.
     * Assumes there's at least one banner to decrement.
     */
    public void decrementBannerCount() { // CD: +decrementBannerCount(): void
        if (currentBannerCount > 0) {
            currentBannerCount--;
        }
    }

    // Getters and Setters (omitted for brevity as fields are public in diagram, but good practice to include)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getMaxBanners() { return maxBanners; }
    public void setMaxBanners(int maxBanners) { this.maxBanners = maxBanners; }
    public int getCurrentBannerCount() { return currentBannerCount; }
    public void setCurrentBannerCount(int currentBannerCount) { this.currentBannerCount = currentBannerCount; }

    @Override
    public String toString() {
        return "PointOfRest{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", maxBanners=" + maxBanners +
               ", currentBannerCount=" + currentBannerCount +
               '}';
    }
}
