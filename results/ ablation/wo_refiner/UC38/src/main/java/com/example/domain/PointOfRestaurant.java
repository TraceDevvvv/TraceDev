package com.example.domain;

/**
 * Entity representing a Point of Restaurant.
 */
public class PointOfRestaurant {
    private String id;
    private String name;
    private int maxBannersAllowed;

    public PointOfRestaurant(String id, String name, int maxBannersAllowed) {
        this.id = id;
        this.name = name;
        this.maxBannersAllowed = maxBannersAllowed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxBannersAllowed() {
        return maxBannersAllowed;
    }

    /**
     * Checks if the point of restaurant can accept a new banner given the current count.
     * Links to requirement REQ-14.
     */
    public boolean canAcceptNewBanner(int currentCount) {
        return currentCount < maxBannersAllowed;
    }
}