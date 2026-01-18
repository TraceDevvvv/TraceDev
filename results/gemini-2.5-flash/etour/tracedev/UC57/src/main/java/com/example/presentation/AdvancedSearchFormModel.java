package com.example.presentation;

import java.util.HashMap;
import java.util.Map;

/**
 * Model representing the data in the advanced search form.
 * Used by the Presentation Layer to bind form inputs.
 */
public class AdvancedSearchFormModel {
    public String keyword;
    public String category;
    public int maxDistanceKm;
    public Map<String, String> otherCriteria;

    /**
     * Default constructor. Initializes otherCriteria map.
     */
    public AdvancedSearchFormModel() {
        this.otherCriteria = new HashMap<>();
    }

    /**
     * Parameterized constructor for convenience.
     * @param keyword The search keyword.
     * @param category The search category.
     * @param maxDistanceKm The maximum distance for search.
     * @param otherCriteria Additional criteria.
     */
    public AdvancedSearchFormModel(String keyword, String category, int maxDistanceKm, Map<String, String> otherCriteria) {
        this.keyword = keyword;
        this.category = category;
        this.maxDistanceKm = maxDistanceKm;
        this.otherCriteria = otherCriteria != null ? new HashMap<>(otherCriteria) : new HashMap<>();
    }

    @Override
    public String toString() {
        return "AdvancedSearchFormModel{" +
               "keyword='" + keyword + '\'' +
               ", category='" + category + '\'' +
               ", maxDistanceKm=" + maxDistanceKm +
               ", otherCriteria=" + otherCriteria +
               '}';
    }
}