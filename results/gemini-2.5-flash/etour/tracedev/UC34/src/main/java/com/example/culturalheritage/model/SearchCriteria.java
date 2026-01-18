package com.example.culturalheritage.model;

/**
 * A Value Object representing the criteria for a cultural heritage search.
 */
public class SearchCriteria {
    private String keyword;
    private String typeFilter;
    private int radius; // Radius in meters

    /**
     * Default constructor.
     */
    public SearchCriteria() {
    }

    /**
     * Constructor with all fields.
     * @param keyword The keyword to search for (e.g., "museum", "park").
     * @param typeFilter The type of cultural heritage to filter by (e.g., "historical site", "art gallery").
     * @param radius The search radius in meters from the user's current location.
     */
    public SearchCriteria(String keyword, String typeFilter, int radius) {
        this.keyword = keyword;
        this.typeFilter = typeFilter;
        this.radius = radius;
    }

    // --- Getters and Setters ---

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
        this.typeFilter = typeFilter;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "keyword='" + keyword + '\'' +
                ", typeFilter='" + typeFilter + '\'' +
                ", radius=" + radius +
                '}';
    }
}