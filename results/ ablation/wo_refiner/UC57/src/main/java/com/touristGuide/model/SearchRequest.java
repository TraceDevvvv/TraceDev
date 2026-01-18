package com.touristGuide.model;

import java.util.Map;

public class SearchRequest {
    private Map<String, Object> filters;
    private Location location;

    public SearchRequest(Map<String, Object> filters, Location location) {
        this.filters = filters;
        this.location = location;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Validate the request; for simplicity, checks location and filters not null.
     */
    public boolean validate() {
        return location != null && filters != null;
    }
}