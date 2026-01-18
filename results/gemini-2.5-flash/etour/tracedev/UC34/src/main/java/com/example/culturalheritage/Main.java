package com.example.culturalheritage;

import com.example.culturalheritage.view.SearchView;
import com.example.culturalheritage.controller.SearchController;
import com.example.culturalheritage.service.CulturalHeritageService;
import com.example.culturalheritage.service.LocationService;
import com.example.culturalheritage.util.GuestUserSession;
import com.example.culturalheritage.repository.CulturalHeritageRepository;
import com.example.culturalheritage.adapter.ETOURSystemAdapter;
import com.example.culturalheritage.model.SearchCriteria;

/**
 * Main class to demonstrate the cultural heritage search functionality.
 * This sets up the application context and simulates user interaction.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Cultural Heritage Search Application ---");

        // 1. Initialize Infrastructure/Data Access Layer
        ETOURSystemAdapter etourSystemAdapter = new ETOURSystemAdapter();
        CulturalHeritageRepository culturalHeritageRepository = new CulturalHeritageRepository(etourSystemAdapter);

        // 2. Initialize Application/Service Layer
        CulturalHeritageService culturalHeritageService = new CulturalHeritageService(culturalHeritageRepository);
        LocationService locationService = new LocationService();

        // 3. Initialize Utility Layer
        GuestUserSession guestUserSession = new GuestUserSession();

        // 4. Initialize Presentation Layer (View and Controller)
        // SearchView needs SearchController for interaction, so we pass a placeholder for now
        // and set it later to avoid circular dependency in constructor.
        // A common pattern is to inject the controller into the view, and the view into the controller.
        // For console app simplicity, we'll instantiate and then 'link'.
        SearchView searchView = new SearchView(null); // Temporarily null, will be set below
        SearchController searchController = new SearchController(
                culturalHeritageService,
                guestUserSession,
                locationService,
                searchView // Pass the view to the controller
        );
        // Now, set the controller for the view. The SearchView constructor was used earlier for dependency injection.
        // To properly link the controller *back* to the view if view's controller was initially null,
        // we might need a setter in SearchView, or reconstruct it.
        searchView = new SearchView(searchController); // Re-instantiate with controller correctly injected.

        System.out.println("\n--- Simulating User Search Scenario (Success Case) ---");
        // Simulate user action: GU -> SV: activateSearchFunctionality() (REQ-004) - M1
        searchView.activateSearchFunctionality(); // Call renamed method

        // Simulate user action: GU -> SV: submitForm() (REQ-006, REQ-007) - M8
        // For the success case, submitForm() will use its default criteria (created internally).
        searchView.submitForm(); // This method now encapsulates getting criteria and calling the controller.

        System.out.println("\n--- Simulating User Search Scenario (ETOUR Connection Interrupted Case) ---");
        // To simulate ETOUR connection error (REQ-011), we'll pass a specific keyword
        // or rely on the random failure in ETOURSystemAdapter.
        // For deterministic testing, let's make a specific criteria for failure.
        SearchCriteria errorCriteria = new SearchCriteria("error_museum", "historical site", 5000);
        System.out.println("Simulating search with criteria designed to trigger ETOUR connection error...");
        
        // Use the new setTestSearchCriteria to prime the SearchView with the error criteria
        searchView.setTestSearchCriteria(errorCriteria);
        searchView.submitForm(); // Now submitForm will use the errorCriteria

        System.out.println("\n--- Application End ---");
    }
}