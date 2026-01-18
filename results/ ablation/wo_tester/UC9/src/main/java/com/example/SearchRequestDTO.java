package com.example;

import java.util.Map;

/**
 * Data Transfer Object for search request criteria.
 */
public class SearchRequestDTO {
    private Map<String, Object> criteria;

    public SearchRequestDTO(Map<String, Object> criteria) {
        this.criteria = criteria;
    }

    public Map<String, Object> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<String, Object> criteria) {
        this.criteria = criteria;
    }
}