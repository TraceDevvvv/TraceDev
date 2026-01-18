package com.example;

import java.util.List;
import java.util.Map;

/**
 * Controller that handles search interactions.
 */
public class SearchController {
    private AuthenticationService authService;
    private SearchService searchService;

    public SearchController(AuthenticationService authService, SearchService searchService) {
        this.authService = authService;
        this.searchService = searchService;
    }

    /**
     * Step 1: User activates search.
     * Entry condition: User IS authenticated (checked via AuthenticationService).
     */
    public void activateSearch() {
        System.out.println("Search activated.");
        // In a real UI, this might trigger navigation to search form.
    }

    /**
     * Step 2: System shows search form.
     * @return a message indicating the form is shown
     */
    public void showSearchForm() {
        System.out.println("Search form displayed.");
    }

    /**
     * Steps 3-4: User fills and submits form.
     * @param criteria the search criteria provided by the user
     */
    public void fillAndSubmitSearch(Map<String, Object> criteria) {
        System.out.println("Search criteria submitted: " + criteria);
        // Create DTO
        SearchRequestDTO dto = createSearchRequestDTO(criteria);
        // Create specification and trigger search
        PointOfRestSpecification spec = searchService.createSpecification(dto.getCriteria());
        // Perform search
        List<PointOfRestDTO> results = searchService.searchPointsOfRest(spec);
        if (results.isEmpty() && !searchService.checkServerConnection()) {
            displayError("Connection lost");
        } else {
            displayResults(results);
        }
    }

    /**
     * Creates a SearchRequestDTO from criteria.
     * @param criteria the search criteria
     * @return the DTO
     */
    public SearchRequestDTO createSearchRequestDTO(Map<String, Object> criteria) {
        return new SearchRequestDTO(criteria);
    }

    /**
     * Step 6: System returns list (success condition).
     * @param dtos list of PointOfRestDTOs to display
     */
    public void displayResults(List<PointOfRestDTO> dtos) {
        System.out.println("=== Search Results ===");
        for (PointOfRestDTO dto : dtos) {
            System.out.println(dto);
        }
        System.out.println("======================");
    }

    /**
     * Displays an error message to the user.
     * Exit condition: Connection interrupted.
     * @param message the error message
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }
}