package com.example.advancedsearch;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the form for advanced search, capturing various search criteria.
 * Tourists fill this form to specify their search preferences.
 */
public class AdvancedSearchForm {
    private String keyword;
    private String category;
    private double maxDistanceKm; // Maximum distance from the tourist's current location
    private double minRating;     // Minimum average rating for a site
    private List<String> amenities; // List of desired amenities (e.g., "parking", "wifi")
    private boolean hasAccessibility; // Whether the site should have accessibility features

    /**
     * Constructs an empty AdvancedSearchForm.
     * Default values are set to allow broad searches initially.
     */
    public AdvancedSearchForm() {
        this.keyword = "";
        this.category = "";
        this.maxDistanceKm = Double.MAX_VALUE; // No distance limit by default
        this.minRating = 0.0; // No minimum rating by default
        this.amenities = new ArrayList<>();
        this.hasAccessibility = false;
    }

    /**
     * Gets the search keyword.
     *
     * @return The keyword.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Sets the search keyword.
     *
     * @param keyword The keyword to set.
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword != null ? keyword : "";
    }

    /**
     * Gets the search category.
     *
     * @return The category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the search category.
     *
     * @param category The category to set.
     */
    public void setCategory(String category) {
        this.category = category != null ? category : "";
    }

    /**
     * Gets the maximum distance in kilometers from the tourist's current location.
     *
     * @return The maximum distance in kilometers.
     */
    public double getMaxDistanceKm() {
        return maxDistanceKm;
    }

    /**
     * Sets the maximum distance in kilometers from the tourist's current location.
     *
     * @param maxDistanceKm The maximum distance to set. Must be non-negative.
     */
    public void setMaxDistanceKm(double maxDistanceKm) {
        if (maxDistanceKm < 0) {
            throw new IllegalArgumentException("Max distance cannot be negative.");
        }
        this.maxDistanceKm = maxDistanceKm;
    }

    /**
     * Gets the minimum average rating for a site.
     *
     * @return The minimum rating.
     */
    public double getMinRating() {
        return minRating;
    }

    /**
     * Sets the minimum average rating for a site.
     *
     * @param minRating The minimum rating to set. Must be between 0.0 and 5.0.
     */
    public void setMinRating(double minRating) {
        if (minRating < 0.0 || minRating > 5.0) {
            throw new IllegalArgumentException("Min rating must be between 0.0 and 5.0.");
        }
        this.minRating = minRating;
    }

    /**
     * Gets the list of desired amenities.
     *
     * @return A list of amenities.
     */
    public List<String> getAmenities() {
        return new ArrayList<>(amenities); // Return a copy to prevent external modification
    }

    /**
     * Sets the list of desired amenities.
     *
     * @param amenities The list of amenities to set.
     */
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities != null ? new ArrayList<>(amenities) : new ArrayList<>();
    }

    /**
     * Adds an amenity to the list of desired amenities.
     *
     * @param amenity The amenity to add.
     */
    public void addAmenity(String amenity) {
        if (amenity != null && !amenity.trim().isEmpty() && !this.amenities.contains(amenity.trim())) {
            this.amenities.add(amenity.trim());
        }
    }

    /**
     * Checks if the site should have accessibility features.
     *
     * @return True if accessibility is required, false otherwise.
     */
    public boolean hasAccessibility() {
        return hasAccessibility;
    }

    /**
     * Sets whether the site should have accessibility features.
     *
     * @param hasAccessibility True if accessibility is required, false otherwise.
     */
    public void setHasAccessibility(boolean hasAccessibility) {
        this.hasAccessibility = hasAccessibility;
    }

    @Override
    public String toString() {
        return "AdvancedSearchForm{" +
               "keyword='" + keyword + '\'' +
               ", category='" + category + '\'' +
               ", maxDistanceKm=" + maxDistanceKm +
               ", minRating=" + minRating +
               ", amenities=" + amenities +
               ", hasAccessibility=" + hasAccessibility +
               '}';
    }
}