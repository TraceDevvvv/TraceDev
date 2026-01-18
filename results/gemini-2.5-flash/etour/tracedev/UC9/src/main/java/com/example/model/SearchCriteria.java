package com.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents search criteria provided by the user.
 * This is a value object in the domain layer.
 */
public class SearchCriteria {
    public String keyword;
    public String typeFilter;
    public int maxDistance; // in kilometers, for example

    public SearchCriteria(String keyword, String typeFilter, int maxDistance) {
        this.keyword = keyword;
        this.typeFilter = typeFilter;
        this.maxDistance = maxDistance;
    }

    /**
     * Converts the search criteria into a map of query parameters
     * suitable for an external API call.
     *
     * @return A map of query parameters.
     */
    public Map<String, String> toQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        if (keyword != null && !keyword.isEmpty()) {
            queryParams.put("keyword", keyword);
        }
        if (typeFilter != null && !typeFilter.isEmpty()) {
            queryParams.put("type", typeFilter);
        }
        if (maxDistance > 0) {
            queryParams.put("maxDistance", String.valueOf(maxDistance));
        }
        return queryParams;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "keyword='" + keyword + '\'' +
                ", typeFilter='" + typeFilter + '\'' +
                ", maxDistance=" + maxDistance +
                '}';
    }
}