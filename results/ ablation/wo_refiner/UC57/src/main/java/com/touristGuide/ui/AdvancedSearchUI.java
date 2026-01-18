
package com.touristGuide.ui;

import com.touristGuide.model.*;
import com.touristGuide.controller.AdvancedSearchController;

/**
 * <<trace>> Requirements 7,8: Show and submit advanced search form.
 */
public class AdvancedSearchUI {
    private AdvancedSearchController controller;
    private TouristUser user;

    public AdvancedSearchUI(AdvancedSearchController controller, TouristUser user) {
        this.controller = controller;
        this.user = user;
    }

    /**
     * Show advanced search form (requirement 7, sequence message m6).
     */
    public void showAdvancedSearchForm() {
        System.out.println("Displaying advanced search form...");
    }

    /**
     * Submit form to controller (requirement 8, sequence message m7).
     */
    public void submitForm(SearchRequest request) {
        SearchResult result = controller.handleSearch(request);
        if (result != null) {
            displayResults(result);
        } else {
            // In real scenario, get validation error from controller.
            ValidationError error = new ValidationError("VALIDATION_ERROR", "Invalid request");
            showValidationError(error);
        }
    }

    /**
     * Display search results (requirement 10, sequence message m30).
     */
    public void displayResults(SearchResult result) {
        System.out.println("Search Results (" + result.getSites().size() + " sites found, query time: " + result.getQueryTime() + " ms):");
        for (Site site : result.getSites()) {
            System.out.println(" - " + site.getName() + " at " + site.getLocation().getLatitude() + "," + site.getLocation().getLongitude());
        }
    }

    /**
     * Show validation error (sequence message m12).
     */
    public void showValidationError(ValidationError error) {
        System.err.println("Validation Error: " + error.getMessage() + " (Code: " + error.getErrorCode() + ")");
    }
}
