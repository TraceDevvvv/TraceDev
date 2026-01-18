package com.example;

/**
 * Controller for handling advanced search operations.
 */
public class AdvancedSearchController {
    private ProcessSearchCommand processSearchCommand;
    private SearchQuery searchQuery;

    /**
     * Constructor for AdvancedSearchController.
     * Initializes with default serv.
     */
    public AdvancedSearchController() {
        this.processSearchCommand = new ProcessSearchService();
        this.searchQuery = new SearchQueryService();
    }

    /**
     * Displays the advanced search form (simulated output).
     */
    public void displayAdvancedSearchForm() {
        System.out.println("Advanced Search Form displayed.");
    }

    /**
     * Handles the search submission from the tourist.
     * Implements the sequence diagram flow including connection check.
     * @param criteria the search criteria.
     * @param location the location of the tourist.
     * @return the search result if successful, null otherwise.
     */
    public SearchResult handleSearchSubmission(SearchCriteria criteria, Location location) {
        // Simulate connection availability. In a real scenario, this would be checked.
        boolean connectionAvailable = true; // Assumption: connection is available by default.
        // For testing the alternate flow, you can set connectionAvailable = false.

        if (!connectionAvailable) {
            System.out.println("ConnectionError: Unable to process search due to connection interruption.");
            return null;
        }

        // Start transaction time for quality requirement.
        long startTime = System.currentTimeMillis();

        // Execute the command (async processing simulated).
        processSearchCommand.execute(criteria, location);
        // Assuming async processing, we continue without waiting.

        // Execute the search query.
        SearchResult result = searchQuery.execute(criteria, location);

        // Check quality requirement: transaction < 15 seconds.
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        if (executionTime > 15000) {
            System.out.println("Warning: Transaction took " + executionTime + " ms, exceeding 15 seconds.");
        }

        // Display results to tourist (simulated).
        System.out.println("Displaying results to tourist.");
        if (result != null) {
            System.out.println("Found " + result.getTotalCount() + " sites.");
        }

        return result;
    }
}