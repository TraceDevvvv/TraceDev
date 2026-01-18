package com.etour.restpoint;

/**
 * RestPoint - represents a location where banners can be placed.
 */
public class RestPoint {
    private String id;
    private String name;
    private int maxBanners;
    private int currentBanners;

    public RestPoint(String id, String name, int maxBanners) {
        this.id = id;
        this.name = name;
        this.maxBanners = maxBanners;
        this.currentBanners = 0;
    }

    public int getMaxBanners() {
        return maxBanners;
    }

    public int getCurrentBanners() {
        return currentBanners;
    }

    // Increments the count of banners associated with this rest point.
    public void incrementBannerCount() {
        if (currentBanners < maxBanners) {
            currentBanners++;
        }
    }

    // Checks if the rest point has reached its banner limit.
    public boolean hasReachedMaxBanners() {
        return currentBanners >= maxBanners;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaxBanners(int maxBanners) {
        this.maxBanners = maxBanners;
    }

    public void setCurrentBanners(int currentBanners) {
        this.currentBanners = currentBanners;
    }
}