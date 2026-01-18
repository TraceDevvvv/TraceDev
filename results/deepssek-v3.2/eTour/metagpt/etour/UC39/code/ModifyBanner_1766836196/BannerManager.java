package com.example.banner;

import java.util.ArrayList;
import java.util.List;

/**
 * BannerManager.java
 * Manages a collection of banners for a restaurant point.
 * Provides methods to list, select, and modify banners with validation.
 * This class is a singleton to ensure a single instance per restaurant point.
 */
public class BannerManager {
    private List<Banner> banners;
    private static BannerManager instance;

    // Private constructor for singleton pattern
    private BannerManager() {
        banners = new ArrayList<>();
        // Initialize with some dummy banners for demonstration
        initializeDummyBanners();
    }

    /**
     * Returns the singleton instance of BannerManager.
     *
     * @return BannerManager instance
     */
    public static BannerManager getInstance() {
        if (instance == null) {
            instance = new BannerManager();
        }
        return instance;
    }

    /**
     * Initializes the manager with some dummy banners for demonstration.
     */
    private void initializeDummyBanners() {
        banners.add(new Banner("1", "/images/banner1.jpg"));
        banners.add(new Banner("2", "/images/banner2.png"));
        banners.add(new Banner("3", "/images/banner3.gif"));
    }

    /**
     * Adds a new banner to the manager.
     *
     * @param banner the banner to add
     */
    public void addBanner(Banner banner) {
        if (banner != null) {
            banners.add(banner);
        }
    }

    /**
     * Returns a copy of the list of all banners.
     *
     * @return list of banners
     */
    public List<Banner> getBanners() {
        return new ArrayList<>(banners); // Return copy to preserve encapsulation
    }

    /**
     * Finds a banner by its ID.
     *
     * @param id the banner id
     * @return the banner with matching id, or null if not found
     */
    public Banner getBannerById(String id) {
        for (Banner banner : banners) {
            if (banner.getId().equals(id)) {
                return banner;
            }
        }
        return null;
    }

    /**
     * Updates the image of a banner identified by bannerId.
     * Validates the new image path before updating.
     *
     * @param bannerId      the id of the banner to update
     * @param newImagePath  the new image path
     * @return true if update successful, false otherwise
     * @throws IllegalArgumentException if validation fails
     */
    public boolean updateBannerImage(String bannerId, String newImagePath) throws IllegalArgumentException {
        Banner banner = getBannerById(bannerId);
        if (banner == null) {
            System.err.println("Banner with id " + bannerId + " not found.");
            return false;
        }

        // Validate the new image
        validateImage(newImagePath);

        // Update the image path
        banner.setImagePath(newImagePath);
        System.out.println("Banner image updated successfully for banner id: " + bannerId);
        return true;
    }

    /**
     * Validates the image path based on simple criteria.
     * This is a stub validation; in a real system, more comprehensive validation
     * (e.g., file size, dimensions, format) would be performed.
     *
     * @param imagePath the image path to validate
     * @throws IllegalArgumentException if the image path is invalid
     */
    private void validateImage(String imagePath) throws IllegalArgumentException {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty.");
        }

        // Check for allowed image extensions
        String lower = imagePath.toLowerCase();
        if (!(lower.endsWith(".jpg") || lower.endsWith(".jpeg") ||
              lower.endsWith(".png") || lower.endsWith(".gif") ||
              lower.endsWith(".bmp"))) {
            throw new IllegalArgumentException("Invalid image format. Allowed formats: .jpg, .jpeg, .png, .gif, .bmp");
        }

        // Additional validation could be added here (e.g., file size, dimensions)
    }

    /**
     * Displays the list of banners in a user-friendly format.
     */
    public void displayBanners() {
        if (banners.isEmpty()) {
            System.out.println("No banners available.");
            return;
        }
        System.out.println("Available Banners:");
        for (Banner banner : banners) {
            System.out.println("  ID: " + banner.getId() + ", Image: " + banner.getImagePath());
        }
    }
}