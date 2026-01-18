package com.example;

/**
 * Represents a Convention, a domain entity that holds information about
 * event-wide banner limits.
 * Annotated with <<Domain Entity>> stereotype.
 */
public class Convention {
    // - maxAllowedBanners : int
    private int maxAllowedBanners;

    /**
     * Constructs a new Convention instance.
     *
     * @param maxAllowedBanners The maximum number of banners allowed for this convention.
     */
    public Convention(int maxAllowedBanners) {
        this.maxAllowedBanners = maxAllowedBanners;
    }

    /**
     * Gets the maximum number of banners allowed for this convention.
     *
     * @return The maximum allowed banners.
     */
    public int getMaxAllowedBanners() {
        return maxAllowedBanners;
    }

    /**
     * Sets the maximum number of banners allowed for this convention.
     * @param maxAllowedBanners The new maximum allowed banners.
     */
    public void setMaxAllowedBanners(int maxAllowedBanners) {
        this.maxAllowedBanners = maxAllowedBanners;
    }
}