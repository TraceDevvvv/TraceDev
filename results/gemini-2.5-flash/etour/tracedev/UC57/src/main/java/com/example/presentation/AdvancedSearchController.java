package com.example.presentation;

import com.example.application.AdvancedSearchService;
import com.example.dto.AdvancedSearchRequestDTO;
import com.example.dto.SiteResultDTO;
import com.example.exceptions.ETOURServiceException;
import com.example.infrastructure.IAuthenticationService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for handling advanced search requests from the user interface.
 * Mediates between the View and the Application Layer.
 */
public class AdvancedSearchController {
    // Controller depends on the application service and authentication service
    private final AdvancedSearchService advancedSearchService;
    private final IAuthenticationService authenticationService; // Added to satisfy requirement R3 (implied)

    // Reference to the view for callbacks, usually handled by a framework
    private AdvancedSearchView view; // Assuming a direct dependency for this example

    /**
     * Constructs an AdvancedSearchController.
     * @param advancedSearchService The application service to delegate search logic to.
     * @param authenticationService The authentication service (placeholder for R3).
     */
    public AdvancedSearchController(AdvancedSearchService advancedSearchService, IAuthenticationService authenticationService) {
        this.advancedSearchService = advancedSearchService;
        this.authenticationService = authenticationService;
    }

    /**
     * Sets the view associated with this controller. In a real application,
     * this might be handled by a framework or dependency injection.
     * @param view The AdvancedSearchView instance.
     */
    public void setView(AdvancedSearchView view) {
        this.view = view;
    }

    /**
     * Handles the request to display the advanced search form.
     * This method is called by the View when the user initiates advanced search.
     */
    public void requestAdvancedSearchForm() {
        System.out.println("AdvancedSearchController: Requesting advanced search form.");
        if (view != null) {
            // In a real application, this might fetch default form values or prepare the form model
            view.displayAdvancedSearchForm(new AdvancedSearchFormModel());
        }
    }

    /**
     * Handles the submission of the advanced search form.
     * This method converts the form data into a DTO and calls the application service.
     *
     * @param request The {@link AdvancedSearchRequestDTO} containing the search parameters.
     *                Note: The sequence diagram implies the DTO is created here, but the method signature shows it's passed.
     *                For implementation, I'll assume the DTO is prepared by the view or a helper,
     *                or we convert from a form model here. Let's make a reasonable assumption:
     *                The view passes the DTO it constructs from its `AdvancedSearchFormModel`.
     */
    public void submitAdvancedSearch(AdvancedSearchRequestDTO request) {
        System.out.println("AdvancedSearchController: Submitting advanced search with DTO: " + request);

        // Precondition: Tourist is authenticated to the system (R3)
        // For demonstration, we assume authentication check happens implicitly or earlier.
        // IAuthenticationService authenticationService.isAuthenticated(); // This would be a real check.

        // Create a tourist event context (R11 related for getUserLocation)
        // This context would usually contain user session, device info, etc.
        Map<String, String> touristEventContext = new HashMap<>();
        touristEventContext.put("userId", "tourist123");
        touristEventContext.put("sessionId", "abc-123");
        // Example to trigger an error in ETOURLocationService for demonstration:
        // touristEventContext.put("simulateError", "true");

        try {
            // Delegate to the application service to process the search
            List<SiteResultDTO> siteResults = advancedSearchService.processAdvancedSearch(request, touristEventContext);
            System.out.println("AdvancedSearchController: Received " + siteResults.size() + " search results from service.");

            if (view != null) {
                // Display results to the user
                view.displaySearchResults(siteResults);
            }
        } catch (ETOURServiceException e) {
            // Handle ETOUR connection interruption (R11)
            System.err.println("AdvancedSearchController: Error during advanced search: " + e.getMessage());
            if (view != null) {
                view.displayError("Failed to retrieve location data: " + e.getMessage());
            }
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("AdvancedSearchController: An unexpected error occurred: " + e.getMessage());
            if (view != null) {
                view.displayError("An unexpected error occurred during search: " + e.getMessage());
            }
        }
    }
}