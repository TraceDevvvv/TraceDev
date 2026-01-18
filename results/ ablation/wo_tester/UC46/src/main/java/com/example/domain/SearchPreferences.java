package com.example.domain;

/**
 * Represents the search preferences of a tourist.
 * Contains categories, price range, distance limit, and keywords.
 */
public class SearchPreferences {
    private String id;
    private String touristId;
    private String[] categories;
    private Range priceRange;
    private int distanceLimit;
    private String[] keywords;

    public SearchPreferences(String id, String touristId, String[] categories, Range priceRange, int distanceLimit, String[] keywords) {
        this.id = id;
        this.touristId = touristId;
        this.categories = categories;
        this.priceRange = priceRange;
        this.distanceLimit = distanceLimit;
        this.keywords = keywords;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Range getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Range priceRange) {
        this.priceRange = priceRange;
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

    // Updates the fields of the search preferences
    public void updateFields(String[] categories, Range priceRange, int distanceLimit, String[] keywords) {
        this.categories = categories;
        this.priceRange = priceRange;
        this.distanceLimit = distanceLimit;
        this.keywords = keywords;
    }

    // Validates the search preferences, including the price range
    public boolean validate() {
        if (priceRange == null || !priceRange.isValid()) {
            return false;
        }
        if (distanceLimit < 0) {
            return false;
        }
        // Additional validation can be added here
        return true;
    }
}