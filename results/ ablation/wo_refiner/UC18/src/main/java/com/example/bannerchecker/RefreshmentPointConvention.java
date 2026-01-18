package com.example.bannerchecker;

/**
 * Represents the convention for a refreshment point, tracking the count of banners placed.
 */
public class RefreshmentPointConvention {
    public String refreshmentPointId;
    public int currentBannerCount;
    public int maxAllowedBanners;

    public RefreshmentPointConvention(String refreshmentPointId, int currentBannerCount, int maxAllowedBanners) {
        this.refreshmentPointId = refreshmentPointId;
        this.currentBannerCount = currentBannerCount;
        this.maxAllowedBanners = maxAllowedBanners;
    }

    public String getRefreshmentPointId() {
        return refreshmentPointId;
    }

    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    public int getMaxAllowedBanners() {
        return maxAllowedBanners;
    }

    /**
     * Gets the current banner count (same as getCurrentBannerCount).
     * Provided to match the class diagram method.
     * @return The current number of banners.
     */
    public int getBannerCount() {
        return currentBannerCount;
    }

    /**
     * Checks if the banner limit for this refreshment point has been exceeded.
     * @return true if current banner count >= max allowed banners, false otherwise.
     */
    public boolean isBannerLimitExceeded() {
        return currentBannerCount >= maxAllowedBanners;
    }

    /**
     * Increments the current banner count by one.
     * This method would be called when a new banner is placed.
     */
    public void incrementBannerCount() {
        this.currentBannerCount++;
    }

    /**
     * Decrements the current banner count by one.
     * This method would be called when a banner is removed.
     */
    public void decrementBannerCount() {
        if (this.currentBannerCount > 0) {
            this.currentBannerCount--;
        }
    }
}