package com.example.dto;

import com.example.domain.Range;
import com.example.domain.SearchPreferences;

/**
 * Data Transfer Object for search preferences.
 * Used to transfer data between layers.
 */
public class PreferencesDTO {
    private String[] categories;
    private double minPrice;
    private double maxPrice;
    private int distanceLimit;
    private String[] keywords;

    public PreferencesDTO(String[] categories, double minPrice, double maxPrice, int distanceLimit, String[] keywords) {
        this.categories = categories;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.distanceLimit = distanceLimit;
        this.keywords = keywords;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getDistanceLimit() {
        return distanceLimit;
    }

    public void setDistanceLimit(int distanceLimit) {
        this.distanceLimit = distanceLimit;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    // Converts this DTO to a domain SearchPreferences object.
    // Package-private as per diagram.
    SearchPreferences toDomain(String touristId) {
        Range priceRange = new Range(minPrice, maxPrice);
        // Assuming ID generation is handled elsewhere; using a placeholder.
        return new SearchPreferences(
                "pref-" + System.currentTimeMillis(),
                touristId,
                categories,
                priceRange,
                distanceLimit,
                keywords
        );
    }
}