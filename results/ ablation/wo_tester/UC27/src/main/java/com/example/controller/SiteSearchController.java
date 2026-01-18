package com.example.controller;

import com.example.model.SearchCriteria;
import com.example.model.SiteSearchResult;
import com.example.service.SiteSearchService;
import com.example.ui.SearchFormUI;

import java.util.List;

/**
 * Controller for site search operations.
 */
public class SiteSearchController {
    private SearchFormUI searchFormUI;
    private SiteSearchService siteSearchService;
    private boolean isSearchAvailable;

    public SiteSearchController(SearchFormUI searchFormUI, SiteSearchService siteSearchService) {
        this.searchFormUI = searchFormUI;
        this.siteSearchService = siteSearchService;
        this.isSearchAvailable = true; // Assume available by default
    }

    /**
     * Main search method triggered by UI.
     *
     * @param searchCriteria the search criteria
     * @return SiteSearchResult
     */
    public SiteSearchResult searchSite(SearchCriteria searchCriteria) {
        if (!validateSearchCriteria(searchCriteria)) {
            searchFormUI.displaySearchResults(new SiteSearchResult("Validation failed: criteria invalid."));
            return new SiteSearchResult("Validation failed.");
        }
        SiteSearchResult result = siteSearchService.executeSearch(searchCriteria);
        // Check execution time limit (quality requirement). Assume limit is 5000 ms.
        if (!result.isWithinTimeLimit(5000)) {
            // Optionally add warning message; for simplicity we just pass the result.
            searchFormUI.displaySearchResults(result);
        } else {
            searchFormUI.displaySearchResults(result);
        }
        return result;
    }

    /**
     * Validates search criteria.
     *
     * @param searchCriteria the criteria to validate
     * @return true if valid, false otherwise
     */
    private boolean validateSearchCriteria(SearchCriteria searchCriteria) {
        // Simplified validation: at least one field should be non-empty.
        return (searchCriteria.getSiteName() != null && !searchCriteria.getSiteName().isEmpty()) ||
                (searchCriteria.getLocation() != null && !searchCriteria.getLocation().isEmpty()) ||
                (searchCriteria.getMetadataFilters() != null && !searchCriteria.getMetadataFilters().isEmpty());
    }

    /**
     * Checks if search functionality is available.
     *
     * @return true if available, false otherwise
     */
    public boolean isSearchFunctionalityAvailable() {
        return isSearchAvailable;
    }
}