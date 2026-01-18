package com.example.validation;

import java.util.Map;

/**
 * Validates search criteria.
 */
public class SearchValidator {
    /**
     * Validates the search criteria.
     * @param criteria the criteria map
     * @return true if criteria are valid, false otherwise
     */
    public boolean validateSearchCriteria(Map<String, Object> criteria) {
        // Example validation: criteria must not be null and must contain at least one key
        if (criteria == null) {
            return false;
        }
        // Additional validation rules can be added here
        // For simulation, we accept any non-null criteria
        return true;
    }
}