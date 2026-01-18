package com.example.banner.application;

/**
 * Result object for the banner count check.
 * Contains status and details.
 */
public class CheckBannerCountResult {
    private boolean canPlaceBanner;
    private int currentCount;
    private int maxCount;
    private String message;

    /**
     * Constructor.
     * @param canPlaceBanner whether a new banner can be placed
     * @param currentCount current number of banners
     * @param maxCount maximum allowed banners
     * @param message descriptive message
     */
    public CheckBannerCountResult(boolean canPlaceBanner, int currentCount, int maxCount, String message) {
        this.canPlaceBanner = canPlaceBanner;
        this.currentCount = currentCount;
        this.maxCount = maxCount;
        this.message = message;
    }

    public boolean canPlaceBanner() {
        return canPlaceBanner;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public String getMessage() {
        return message;
    }
}