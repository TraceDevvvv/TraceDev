package domain;

/**
 * Represents a PointOfRestaurant entity in the domain.
 * Each PointOfRestaurant has a unique ID, a name, and a limit on the number of banners it can display.
 */
public class PointOfRestaurant {
    private String id;
    private String name;
    private int maxBanners;
    private int currentBannerCount;

    /**
     * Constructs a new PointOfRestaurant.
     *
     * @param id The unique ID of the PointOfRestaurant.
     * @param name The name of the PointOfRestaurant.
     * @param maxBanners The maximum number of banners this PoR can have.
     */
    public PointOfRestaurant(String id, String name, int maxBanners) {
        this.id = id;
        this.name = name;
        this.maxBanners = maxBanners;
        this.currentBannerCount = 0; // Initialize with no banners
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

    public int getCurrentBannerCount() {
        return currentBannerCount;
    }

    /**
     * Checks if a new banner can be added to this PointOfRestaurant.
     *
     * @return true if the current banner count is less than the maximum allowed, false otherwise.
     */
    public boolean canAddBanner() {
        return currentBannerCount < maxBanners;
    }

    /**
     * Increments the count of banners for this PointOfRestaurant.
     * This should typically be called after a banner has been successfully added.
     */
    public void incrementBannerCount() {
        if (currentBannerCount < maxBanners) {
            currentBannerCount++;
        } else {
            // Log or throw an exception if trying to increment beyond max, though canAddBanner() should prevent this.
            System.err.println("Warning: Attempted to increment banner count beyond max for PoR: " + id);
        }
    }

    /**
     * Decrements the count of banners for this PointOfRestaurant.
     * This might be called if a banner is removed or cancelled.
     */
    public void decrementBannerCount() {
        if (currentBannerCount > 0) {
            currentBannerCount--;
        } else {
            System.err.println("Warning: Attempted to decrement banner count below zero for PoR: " + id);
        }
    }

    /**
     * Sets the current banner count. Used by repositories for loading state.
     * @param currentBannerCount The actual current count.
     */
    public void setCurrentBannerCount(int currentBannerCount) {
        this.currentBannerCount = currentBannerCount;
    }
}