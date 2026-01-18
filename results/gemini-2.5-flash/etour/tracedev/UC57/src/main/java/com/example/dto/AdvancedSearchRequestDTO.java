package com.example.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for advanced search requests.
 * Carries data from the presentation layer to the application layer.
 */
public class AdvancedSearchRequestDTO {
    public String keyword;
    public String category;
    public int maxDistanceKm;
    public Map<String, String> otherCriteria; // Added to satisfy requirement R6, R7, R9 (implied)

    /**
     * Default constructor. Initializes otherCriteria map.
     */
    public AdvancedSearchRequestDTO() {
        this.otherCriteria = new HashMap<>();
    }

    /**
     * Constructor with all fields.
     * @param keyword The keyword for search.
     * @param category The category for search.
     * @param maxDistanceKm The maximum distance in kilometers.
     * @param otherCriteria Additional search criteria as a map.
     */
    public AdvancedSearchRequestDTO(String keyword, String category, int maxDistanceKm, Map<String, String> otherCriteria) {
        this.keyword = keyword;
        this.category = category;
        this.maxDistanceKm = maxDistanceKm;
        this.otherCriteria = otherCriteria != null ? new HashMap<>(otherCriteria) : new HashMap<>();
    }

    @Override
    public String toString() {
        return "AdvancedSearchRequestDTO{" +
               "keyword='" + keyword + '\'' +
               ", category='" + category + '\'' +
               ", maxDistanceKm=" + maxDistanceKm +
               ", otherCriteria=" + otherCriteria +
               '}';
    }
}