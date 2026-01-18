package com.example;

import com.example.application.AdvancedSearchService;
import com.example.dto.AdvancedSearchRequestDTO;
import com.example.infrastructure.ETOURLocationService;
import com.example.infrastructure.IAuthenticationService;
import com.example.infrastructure.ILocationService;
import com.example.infrastructure.ISiteRepository;
import com.example.infrastructure.SiteRepositoryImpl;
import com.example.presentation.AdvancedSearchController;
import com.example.presentation.AdvancedSearchFormModel;
import com.example.presentation.AdvancedSearchView;
import com.example.infrastructure.IAuthenticationService; // Re-import for clarity if not already

import java.util.HashMap;
import java.util.Map;

/**
 * Main application class to demonstrate the interaction flow described in the Sequence Diagram.
 * This class acts as the "Tourist" and the initial system setup.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("Starting Tourist Advanced Search Application Demo...\n");

        // --- System Initialization ---
        // Infrastructure Layer
        ISiteRepository siteRepository = new SiteRepositoryImpl();
        ILocationService locationService = new ETOURLocationService();
        IAuthenticationService authenticationService = new IAuthenticationService() {
            // Dummy authentication service for R3, no actual implementation needed for this demo
            // boolean isAuthenticated() { return true; }
        };

        // Application Layer
        AdvancedSearchService advancedSearchService = new AdvancedSearchService(siteRepository, locationService);

        // Presentation Layer
        AdvancedSearchController advancedSearchController = new AdvancedSearchController(advancedSearchService, authenticationService);
        AdvancedSearchView advancedSearchView = new AdvancedSearchView();
        advancedSearchView.setController(advancedSearchController);
        advancedSearchController.setView(advancedSearchView); // Inject view into controller for callbacks

        System.out.println("System initialized. Simulating Tourist interaction.\n");

        // --- Sequence Diagram Flow ---

        // Precondition: Tourist is authenticated to the system (R3)
        // (Implicitly assumed for this demo setup)
        System.out.println("Tourist: (Precondition) User is authenticated.");

        // Flow of Events 1: Tourist enables the advanced search feature
        long startTime = System.currentTimeMillis(); // Start timing constraint for R12
        advancedSearchView.enableAdvancedSearch(); // Tourist -> View : enableAdvancedSearch()

        // Flow of Events 2: System displays the advanced search form.
        // Handled by controller.requestAdvancedSearchForm() -> view.displayAdvancedSearchForm()

        // Flow of Events 3: Tourist fills in the form of advanced search.
        Map<String, String> customCriteria = new HashMap<>();
        customCriteria.put("amenity", "wifi");
        customCriteria.put("rating", "4+ stars");

        AdvancedSearchFormModel touristInput = new AdvancedSearchFormModel(
            "museum",       // keyword
            "art",          // category
            50,             // maxDistanceKm (within 50km)
            customCriteria  // otherCriteria
        );
        advancedSearchView.fillAdvancedSearchForm(touristInput); // Tourist -> View : fillAdvancedSearchForm()

        // Flow of Events 4: Tourist submits the form.
        advancedSearchView.submitForm(); // Tourist -> View : submitForm()
        // This triggers View -> Controller : submitAdvancedSearch()
        // And Controller -> AppService : processAdvancedSearch()
        // And AppService -> LocationSvc : getUserLocation()
        // And LocationSvc -> ETOUR -> LocationSvc (simulated in ETOURLocationService)
        // And AppService -> SiteRepo : findByCriteriaAndLocation()
        // And SiteRepo -> DB -> SiteRepo (simulated in SiteRepositoryImpl)
        // And AppService -> Controller : return siteResults
        // And Controller -> View : displaySearchResults
        // And View -> Tourist : displaySearchResults (printed to console)

        System.out.println("\n--- Simulating a search with ETOUR service error ---\n");
        // Simulate ETOUR connection interruption (R11)
        AdvancedSearchFormModel errorScenarioInput = new AdvancedSearchFormModel(
                "park",
                "nature",
                100,
                new HashMap<>() {{ put("simulateError", "true"); }} // Trigger error in ETOURLocationService
        );
        advancedSearchView.fillAdvancedSearchForm(errorScenarioInput);
        advancedSearchView.submitForm();


        long endTime = System.currentTimeMillis(); // End timing constraint for R12
        long duration = endTime - startTime;
        System.out.println("\n--- Transaction Completion ---");
        System.out.printf("Total duration for advanced search interaction: %d ms (R12 constraint: <= 15000ms).\n", duration);
        if (duration <= 15000) {
            System.out.println("R12: Transaction completed within 15 seconds.");
        } else {
            System.out.println("R12: WARNING - Transaction exceeded 15 seconds.");
        }

        System.out.println("\nDemo Finished.");
    }
}