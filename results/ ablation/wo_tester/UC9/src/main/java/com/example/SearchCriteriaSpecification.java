package com.example;

import java.util.Map;

/**
 * Concrete implementation of PointOfRestSpecification based on search criteria.
 */
public class SearchCriteriaSpecification implements PointOfRestSpecification {
    private Map<String, Object> criteria;

    public SearchCriteriaSpecification(Map<String, Object> criteria) {
        this.criteria = criteria;
    }

    public Map<String, Object> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<String, Object> criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean isSatisfiedBy(PointOfRest pointOfRest) {
        // Delegates to evaluateCriteria
        return evaluateCriteria(pointOfRest);
    }

    @Override
    public boolean evaluateCriteria(PointOfRest pointOfRest) {
        if (criteria == null || criteria.isEmpty()) {
            return true; // No criteria -> all points satisfy
        }
        // Simplified evaluation: check if point's name contains the "name" criteria (if present)
        if (criteria.containsKey("name")) {
            String requiredName = (String) criteria.get("name");
            if (!pointOfRest.getName().toLowerCase().contains(requiredName.toLowerCase())) {
                return false;
            }
        }
        // Additional criteria could be added here (location, amenities, etc.)
        return true;
    }
}