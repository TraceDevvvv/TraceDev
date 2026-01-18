package com.example.culturalobjects;

/**
 * Main class to demonstrate the Cultural Objects Search application.
 * This class sets up the dependencies and simulates user interactions
 * as described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Cultural Objects Search Application ---");

        // 1. Initialize all components (Dependency Injection)
        System.out.println("\nInitializing application components...");
        ETOURGateway etourGateway = new ETOURGateway();
        ICulturalObjectRepository culturalObjectRepository = new CulturalObjectRepositoryImpl(etourGateway);
        SearchService searchService = new SearchService(culturalObjectRepository);
        SearchView searchView = new SearchView();
        SearchController searchController = new SearchController(searchService, searchView);
        System.out.println("Components initialized.");

        // Simulate User Interaction: Successful Search Flow
        System.out.println("\n=== Scenario 1: Successful Search ===");
        // User -> Controller: activateSearchFunctionality()
        searchController.activateSearchFunctionality(true); // REQ-003: System IS ready

        // User -> View: fillAndSubmitForm(searchFormDTO: SearchFormDTO)
        SearchFormDTO successfulSearchForm = new SearchFormDTO("ancient art", "Painting", "Paris");
        searchView.fillAndSubmitForm(successfulSearchForm, searchController);

        // Simulate User Interaction: Search with no results (assuming ETOURGateway can simulate this based on query)
        System.out.println("\n=== Scenario 2: Search with No Results ===");
        SearchFormDTO noResultSearchForm = new SearchFormDTO("nonexistent object", "Invalid Category", "Neverland");
        searchView.fillAndSubmitForm(noResultSearchForm, searchController);


        // Simulate User Interaction: ETOUR Connection Interrupted Flow
        System.out.println("\n=== Scenario 3: ETOUR Connection Failure ===");
        // Configure ETOURGateway to simulate a connection failure
        etourGateway.setSimulateConnectionFailure(true);
        SearchFormDTO failedSearchForm = new SearchFormDTO("any keywords", "any category", "any location");
        searchView.fillAndSubmitForm(failedSearchForm, searchController);
        // Reset connection failure for subsequent operations if any
        etourGateway.setSimulateConnectionFailure(false);

        System.out.println("\n--- Cultural Objects Search Application Finished ---");
    }
}