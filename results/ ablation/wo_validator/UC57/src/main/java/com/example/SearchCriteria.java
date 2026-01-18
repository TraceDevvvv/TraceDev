package com.example;

import java.util.Map;
import java.util.HashMap;

/**
 * Represents search criteria with filters, sorting, and distance limit.
 */
public class SearchCriteria {
    private Map<String, Object> filters;
    private String sortBy;
    private double maxDistance;

    /**
     * Constructor for SearchCriteria.
     */
    public SearchCriteria() {
        this.filters = new HashMap<>();
        this.sortBy = "";
        this.maxDistance = 0.0;
    }

    /**
     * Sets a filter key-value pair.
     * @param key the filter key.
     * @param value the filter value.
     */
    public void setFilter(String key, Object value) {
        filters.put(key, value);
    }

    /**
     * Gets the filters map.
     * @return the filters.
     */
    public Map<String, Object> getFilters() {
        return filters;
    }

    /**
     * Sets the sort field.
     * @param sortBy the field to sort by.
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Gets the sort field.
     * @return the sort field.
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets the maximum distance.
     * @param maxDistance the maximum distance.
     */
    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * Gets the maximum distance.
     * @return the maximum distance.
     */
    public double getMaxDistance() {
        return maxDistance;
    }
}