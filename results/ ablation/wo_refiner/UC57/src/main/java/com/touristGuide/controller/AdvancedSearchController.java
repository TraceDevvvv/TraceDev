package com.touristGuide.controller;

import com.touristGuide.model.*;
import com.touristGuide.usecase.AdvancedSearchUseCase;
import java.util.List;
import java.util.Map;

/**
 * <<trace>> Requirement 5: Flow of events User System.
 */
public class AdvancedSearchController {
    private AdvancedSearchUseCase useCase;
    private TouristUser currentUser; // Assuming authenticated user is set.

    public AdvancedSearchController(AdvancedSearchUseCase useCase, TouristUser user) {
        this.useCase = useCase;
        this.currentUser = user;
    }

    /**
     * Main search handling (transaction time ≤ 15 sec, requirement 13).
     */
    public SearchResult handleSearch(SearchRequest request) {
        long startTime = System.currentTimeMillis();

        // Validate request
        if (!validateRequest(request)) {
            ValidationError error = createValidationError("VALIDATION_ERROR", "Invalid request parameters");
            // In real scenario, throw exception or return error wrapper; for simplicity, return null.
            // Sequence diagram shows controller returns ValidationError to UI.
            // We'll handle this differently in UI interaction.
            return null;
        }

        // Ensure user is authenticated (requirement 4)
        if (currentUser == null || !currentUser.authenticated()) {
            // Should not happen if UI authenticated earlier.
            return null;
        }

        // Create SearchQuery from request
        Map<String, Object> filters = request.getFilters();
        // Simplified: create criteria from filters; assume eventType key.
        String eventType = (String) filters.get("eventType");
        Map<String, String> attributes = Map.of(); // dummy
        SearchCriteria criteria = new SearchCriteria(attributes, eventType);
        SearchQuery query = new SearchQuery(criteria, request.getLocation());

        // Process search via use case
        List<Site> sites = useCase.execute(query);

        // Compute query time
        long queryTime = System.currentTimeMillis() - startTime;
        SearchResult result = new SearchResult(sites, queryTime);

        // Validate against quality requirement (max transaction time)
        if (!result.isWithinTimeLimit()) {
            // <<trace>> Requirement 13 violation: flag warning (could be logged).
            System.err.println("Warning: Transaction exceeded 15 seconds.");
        }

        return result;
    }

    /**
     * Validates the search request.
     */
    private boolean validateRequest(SearchRequest request) {
        return request != null && request.validate();
    }

    /**
     * Creates a validation error for error handling (sequence message m10).
     */
    public ValidationError createValidationError(String errorCode, String message) {
        return new ValidationError(errorCode, message);
    }

    // For sequence diagram: processSearch and createSearchResult are internal steps.
}