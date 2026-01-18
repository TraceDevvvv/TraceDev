package com.example;

/**
 * Represents a Refreshment Point, a domain entity where banners might be displayed.
 * It contains information about its ID, name, current banner count, and
 * the associated Convention rules.
 * Annotated with <<Domain Entity>> stereotype.
 */
public class RefreshmentPoint {
    // - id : String
    private String id;
    // - name : String
    private String name;
    // - currentBannersCount : int
    private int currentBannersCount;
    // - convention : Convention
    private Convention convention;

    /**
     * Constructs a new RefreshmentPoint instance.
     *
     * @param id The unique identifier of the refreshment point.
     * @param name The name of the refreshment point.
     * @param currentBannersCount The current number of banners at this refreshment point.
     * @param convention The convention associated with this refreshment point,
     *                   which dictates banner rules.
     */
    public RefreshmentPoint(String id, String name, int currentBannersCount, Convention convention) {
        this.id = id;
        this.name = name;
        this.currentBannersCount = currentBannersCount;
        this.convention = convention;
    }

    /**
     * Gets the ID of the refreshment point.
     *
     * @return The refreshment point ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the refreshment point.
     *
     * @return The refreshment point name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current number of banners at this refreshment point.
     *
     * @return The current banner count.
     */
    public int getCurrentBannersCount() {
        return currentBannersCount;
    }

    /**
     * Checks if the current number of banners exceeds the limit set by the associated convention.
     * This method uses the associated Convention to get the maximum allowed banners.
     *
     * @return true if the banner limit is exceeded, false otherwise.
     */
    public boolean isBannerLimitExceeded() {
        // RefreshmentPoint uses Convention in its isBannerLimitExceeded logic.
        return currentBannersCount >= convention.getMaxAllowedBanners();
    }

    /**
     * Gets the associated Convention object.
     * @return The Convention object.
     */
    public Convention getConvention() {
        return convention;
    }
}