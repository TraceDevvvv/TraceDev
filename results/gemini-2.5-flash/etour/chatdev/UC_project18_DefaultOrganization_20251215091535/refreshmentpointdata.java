/*
 * Represents the data for a refreshment point,
 * including its identifier, maximum allowed banners,
 * and current number of banners.
 */
package com.chatdev.bannerapp;
public class RefreshmentPointData {
    private String name;
    private int maxBanners;
    private int currentBanners;
    /*
     * Constructor for RefreshmentPointData.
     * Initializes a new refreshment point with the given details.
     * Performs basic validation to ensure banner counts are not negative.
     *
     * @param name The name or identifier of the refreshment point.
     * @param maxBanners The maximum number of banners allowed for this point.
     * @param currentBanners The current number of banners at this point.
     * @throws IllegalArgumentException if banner counts (maxBanners or currentBanners) are negative.
     */
    public RefreshmentPointData(String name, int maxBanners, int currentBanners) {
        // Ensure banner counts are not negative to maintain data integrity.
        if (maxBanners < 0 || currentBanners < 0) {
            throw new IllegalArgumentException("Banner counts cannot be negative.");
        }
        this.name = name;
        this.maxBanners = maxBanners;
        this.currentBanners = currentBanners;
    }
    /*
     * Retrieves the name of the refreshment point.
     * @return The name of the refreshment point.
     */
    public String getName() {
        return name;
    }
    /*
     * Retrieves the maximum number of banners allowed for this point.
     * @return The maximum number of banners.
     */
    public int getMaxBanners() {
        return maxBanners;
    }
    /*
     * Retrieves the current number of banners at this point.
     * @return The current number of banners.
     */
    public int getCurrentBanners() {
        return currentBanners;
    }
    /*
     * Checks if a new banner can theoretically be added to this refreshment point.
     * This is a simple data-level check, comparing current banners to the maximum allowed.
     *
     * @return true if the current number of banners is strictly less than the maximum allowed banners,
     *         indicating there is space for a new banner; false otherwise.
     */
    public boolean canAddMoreBanners() {
        return currentBanners < maxBanners;
    }
}