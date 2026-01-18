package com.example;

import com.example.model.Tourist;
import com.example.view.AdvancedSearchForm;
import com.example.view.SearchForm;
import com.example.controller.SiteSearchController;
import com.example.repository.SiteRepositoryImpl;
import com.example.service.LocationServiceImpl;
import com.example.service.AuthenticationServiceImpl;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to simulate the advanced site search use case.
 * This demonstrates the interaction flow from the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Setup components
        Tourist tourist = new Tourist("T001", "Alice");
        AdvancedSearchForm form = new AdvancedSearchForm();
        SiteRepositoryImpl repository = new SiteRepositoryImpl();
        LocationServiceImpl locationService = new LocationServiceImpl();
        SiteSearchController controller = new SiteSearchController(repository, locationService);
        AuthenticationServiceImpl authService = new AuthenticationServiceImpl();
        SearchForm searchForm = new SearchForm();

        // Authenticate tourist (as per class diagram)
        boolean authenticated = tourist.authenticate(authService);
        if (!authenticated) {
            System.out.println("Authentication failed.");
            return;
        }

        System.out.println("=== Advanced Site Search Simulation ===");

        // Step 1: Tourist enables advanced search
        form.enableAdvancedSearch();

        // Step 2: Tourist fills the form with filters
        Map<String, Object> filters = new HashMap<>();
        filters.put("category", "Museum");
        filters.put("rating", 4.5);
        form.fillForm(filters);

        // Step 3: Tourist submits the form (which internally calls controller)
        try {
            form.submitForm(controller);
        } catch (RuntimeException e) {
            tourist.displayError(e.getMessage());
        }

        // Sequence diagram missing items simulation
        System.out.println("\n=== Sequence Diagram Missing Items Simulation ===");
        // m2: return displayForm() from SearchForm to Tourist
        searchForm.displayForm();
        // m4: return formDisplayed from SearchForm to Tourist (same as m2 in our simplified version)
        // m10: return locationData from Tourist to Controller
        tourist.getLocationData();
        // m24: return emptyList from Repository to Controller
        repository.getEmptyList();
        // m25: note "Connection interruption handled" on Repository
        repository.handleConnectionInterruption();
        // m26: sync displayResults(sites) from Controller to SearchForm
        controller.displayResults(repository.getEmptyList());
        // m27: return showSiteList(sites) from SearchForm to Tourist
        searchForm.showSiteList(repository.getEmptyList());

        System.out.println("=== Simulation Complete ===");
    }
}