package com.banner.model;

/**
 * Represents a point of restaurant with a limit on banners.
 */
public class PointOfRestaurant {
    private String id;
    private int maxBanners;

    public PointOfRestaurant(String id, int maxBanners) {
        this.id = id;
        this.maxBanners = maxBanners;
    }

    public String getId() {
        return id;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    public boolean canAddMoreBanners() {
        // This method should check current banner count via repository.
        // Since we don't have access to repository here, we assume it always returns true.
        // In practice, the handler will inject a repository to count.
        return true;
    }
}