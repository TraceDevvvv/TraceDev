package com.example.culturalheritage.controller;

import com.example.culturalheritage.model.SearchCriteria;
import com.example.culturalheritage.model.SearchResult;
import com.example.culturalheritage.model.Location;
import com.example.culturalheritage.service.CulturalHeritageService;
import com.example.culturalheritage.service.LocationService;
import com.example.culturalheritage.util.GuestUserSession;
import com.example.culturalheritage.view.SearchView;
import com.example.culturalheritage.exception.SearchFailedException;

/**
 * Handles user interactions related to searching cultural heritage.
 * Acts as the 'Controller' in an MVC pattern, mediating between the View and the Serv.
 */
public class SearchController {
    private CulturalHeritageService culturalHeritageService;
    private GuestUserSession guestUserSession;
    private LocationService locationService;
    private SearchView searchView; // The controller needs to interact with its view

    /**
     * Constructor for SearchController.
     * Injects all necessary service and utility dependencies.
     * @param culturalHeritageService The service for cultural heritage operations.
     * @param guestUserSession The utility for managing guest user sessions.
     * @param locationService The service for location-related operations.
     * @param searchView The view component this controller manages.
     */
    public SearchController(CulturalHeritageService culturalHeritageService,
                            GuestUserSession guestUserSession,
                            LocationService locationService,
                            SearchView searchView) {
        this.culturalHeritageService = culturalHeritageService;
        this.guestUserSession = guestUserSession;
        this.locationService = locationService;
        this.searchView = searchView;
    }

    /**
     * Handles the event when a user activates the search feature (REQ-004).
     * This typically leads to displaying a search form.
     */
    public void handleActivateSearch() {
        System.out.println("SearchController: handleActivateSearch() - Delegate to view to display form.");
        // Step 1: User activates search (handled by SearchView).
        // Step 2: System displays form.
        searchView.displayForm();
    }

    /**
     * Handles the event when a user submits search criteria (REQ-006, REQ-007).
     * This method orchestrates the actual search process.
     * @param criteria The SearchCriteria submitted by the user.
     */
    public void handleSubmitSearch(SearchCriteria criteria) {
        System.out.println("SearchController: handleSubmitSearch() with criteria: " + criteria.getKeyword());

        // Entry Condition: Guest User is logged on.
        // Retrieve the logged-in user ID from the guest user session.
        String userId = guestUserSession.getLoggedInUserId();
        System.out.println("SearchController: Retrieved logged-in user ID: " + userId);

        // Step 5: System gets Guest User's position.
        // Use the LocationService to get the current user's geographical position.
        Location userLocation = locationService.getCurrentUserPosition(userId);
        System.out.println("SearchController: Retrieved user location: " + userLocation.getLatitude() + ", " + userLocation.getLongitude());

        // Step 6: System processes the search.
        // Call the CulturalHeritageService to perform the actual search.
        try {
            SearchResult searchResult = culturalHeritageService.searchCulturalHeritage(criteria, userLocation);
            // Successful Search (Exit Condition: Displays list of sites)
            searchView.displayResults(searchResult);
        } catch (SearchFailedException e) {
            // ETOUR Connection Interrupted (Exit Condition: Connection to server ETOUR is interrupted)
            // If the search fails (e.g., due to ETOUR connection issues), display an error message.
            System.err.println("SearchController: Search failed: " + e.getMessage());
            searchView.displayErrorMessage("Failed to retrieve data from ETOUR: " + e.getMessage());
        }
    }
}