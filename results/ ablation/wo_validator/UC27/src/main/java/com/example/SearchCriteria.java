package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents search criteria with a search term and additional filters.
 */
public class SearchCriteria {
    private String searchTerm;
    private Map<String, Object> filters;

    public SearchCriteria() {
        this.filters = new HashMap<>();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    /**
     * Adds a filter key-value pair to the criteria.
     * @param key the filter key
     * @param value the filter value
     */
    public void addFilter(String key, Object value) {
        this.filters.put(key, value);
    }
}