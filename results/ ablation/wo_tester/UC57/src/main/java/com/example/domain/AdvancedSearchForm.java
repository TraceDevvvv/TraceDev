package com.example.domain;

import com.example.application.AdvancedSearchController;
import com.example.application.SearchResultDTO;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an advanced search form that captures search criteria.
 */
public class AdvancedSearchForm {
    private Map<String, Object> fields;
    private SearchCriteria criteria;

    public AdvancedSearchForm() {
        this.fields = new HashMap<>();
    }

    /**
     * Populates the form fields based on the given search criteria.
     */
    public void populate(SearchCriteria criteria) {
        this.criteria = criteria;
        fields.put("name", criteria.getName());
        fields.put("category", criteria.getCategory());
        fields.put("preferredLocation", criteria.getPreferredLocation());
        fields.put("distanceFromUser", criteria.getDistanceFromUser());
        fields.put("minRating", criteria.getMinRating());
        fields.put("amenities", criteria.getAmenities());
        fields.put("createdDate", criteria.getCreatedDate());
    }

    /**
     * Submits the form, returning a search request.
     * In this implementation, returns null as SearchRequest not defined in UML.
     * Instead, we assume the form triggers a controller directly.
     */
    public SearchResultDTO submit(AdvancedSearchController controller, String userId) {
        if (criteria == null) {
            System.out.println("Form not populated with criteria.");
            return null;
        }
        return controller.executeSearch(userId, criteria);
    }

    // Helper method to get criteria (for internal use)
    public SearchCriteria getCriteria() {
        return criteria;
    }

    // The following methods correspond to sequence diagram interactions
    public void fillForm(SearchCriteria criteria) {
        this.populate(criteria);
        System.out.println("Form filled with criteria: " + criteria.getName());
    }

    public SearchResultDTO submitForm(AdvancedSearchController controller, String userId) {
        return this.submit(controller, userId);
    }

    // Sequence diagram messages implementation

    public String formInterface() {
        return "AdvancedSearchForm interface";
    }

    public String formDisplayed() {
        return "Form displayed";
    }

    public String formUpdated() {
        return "Form updated";
    }

    public SearchResultDTO displayResultsList() {
        // This is a placeholder: In sequence diagram, it returns a list.
        SearchResultDTO result = new SearchResultDTO();
        return result;
    }
}