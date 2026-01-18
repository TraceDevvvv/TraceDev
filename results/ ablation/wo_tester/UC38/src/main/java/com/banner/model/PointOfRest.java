package com.banner.model;

/**
 * Represents a PointOfRest (restaurant location) with a maximum banner capacity.
 */
public class PointOfRest {
    private String id;
    private String name;
    private int maxBanners;

    public PointOfRest(String id, String name, int maxBanners) {
        this.id = id;
        this.name = name;
        this.maxBanners = maxBanners;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    /**
     * Check if the point can accept more banners given the current count.
     */
    public boolean canAcceptMoreBanners(int currentCount) {
        return currentCount < maxBanners;
    }
}