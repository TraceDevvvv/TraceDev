package com.system.entities;

/**
 * Represents a refreshment point convention with a limit on allowed banners.
 */
public class RefreshmentPointConvention {
    private String id;
    private String name;
    private int maxAllowedBanners;
    private int currentBannerCount;

    public RefreshmentPointConvention(String id, String name, int maxAllowedBanners, int currentBannerCount) {
        this.id = id;
        this.name = name;
        this.maxAllowedBanners = maxAllowedBanners;
        this.currentBannerCount = currentBannerCount;
    }

    public String getId() {
        return id;
    }

    public int getMaxAllowedBanners() {
        return maxAllowedBanners;
    }

    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    public boolean isBannerLimitReached() {
        return currentBannerCount >= maxAllowedBanners;
    }
}