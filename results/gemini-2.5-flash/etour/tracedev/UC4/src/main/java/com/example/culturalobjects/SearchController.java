package com.example.culturalobjects;

/**
 * CONTROLLER: Handles user input and orchestrates the search functionality.
 * Acts as the entry point for user interaction with the search feature.
 * REQ-003: Entry Condition: System IS ready. This implies that the Controller and its
 * dependencies are properly initialized before `activateSearchFunctionality` is called.
 */
public class SearchController {

    // Dependencies: SearchService for business logic, SearchView for UI interaction
    private final SearchService searchService;
    private final SearchView searchView;

    /**
     * Constructor for SearchController.
     * @param searchService The SearchService instance to handle search logic.
     * @param searchView The SearchView instance to manage UI display.
     */
    public SearchController(SearchService searchService, SearchView searchView) {
        this.searchService = searchService;
        this.searchView = searchView;
    }

    /**
     * Activates the search functionality.
     * Called by the user (or system) to initiate the search process.
     *
     * @param systemReady A placeholder to acknowledge REQ-003: Entry Condition: System IS ready.
     *                    In a real system, this would be an implicit condition based on application startup.
     */
    public void activateSearchFunctionality(boolean systemReady) {
        if (!systemReady) {
            System.err.println("[Controller] System not ready. Cannot activate search functionality.");
            return;
        }
        System.out.println("[Controller] Activating search functionality...");
        // Displays the search form to the user
        searchView.displaySearchForm();
    }

    /**
     * Submits a search form with user-provided data.
     * This method processes the SearchFormDTO, transforms it into SearchCriteria,
     * and delegates the search operation to the SearchService.
     * Modified to satisfy requirement REQ-006.
     *
     * @param searchFormDTO The Data Transfer Object containing user's search input.
     */
    public void submitSearchForm(SearchFormDTO searchFormDTO) {
        System.out.println("[Controller] User submitted search form with DTO: " + searchFormDTO);

        // Note: Controller transforms SearchFormDTO to SearchCriteria
        // This separation ensures domain criteria are distinct from UI form data.
        SearchCriteria criteria = new SearchCriteria(
                searchFormDTO.getKeywords(),
                searchFormDTO.getCategory(),
                searchFormDTO.getLocation()
        );
        System.out.println("[Controller] Transformed to SearchCriteria: " + criteria);

        // Delegate search logic to the SearchService
        Object serviceResponse = searchService.searchCulturalObjects(criteria);

        // Handle the response from the SearchService
        if (serviceResponse instanceof SearchResultDTO) {
            SearchResultDTO searchResultDTO = (SearchResultDTO) serviceResponse;
            System.out.println("[Controller] SearchService returned results. Displaying to view.");
            // Display successful search results
            searchView.displaySearchResults(searchResultDTO);
        } else if (serviceResponse instanceof ErrorResultDTO) {
            ErrorResultDTO errorResultDTO = (ErrorResultDTO) serviceResponse;
            System.err.println("[Controller] SearchService returned an error: " + errorResultDTO.getMessage());
            // Display error message
            searchView.displayErrorMessage(errorResultDTO.getMessage()); // REQ-009: Added error display
        } else {
            System.err.println("[Controller] Received unexpected response type from SearchService.");
            searchView.displayErrorMessage("An unknown error occurred during search.");
        }
    }
}