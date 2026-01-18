package com.example.model;

import java.util.Map;
import java.util.HashMap;

/**
 * Encapsulates the filters for a site search.
 */
public class SearchCriteria {
    private Map<String, Object> filters;

    public SearchCriteria(Map<String, Object> filters) {
        this.filters = filters != null ? new HashMap<>(filters) : new HashMap<>();
    }

    public Map<String, Object> getFilters() {
        return new HashMap<>(filters);
    }
}