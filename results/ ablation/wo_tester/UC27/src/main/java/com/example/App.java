package com.example;

import com.example.controller.SiteSearchController;
import com.example.db.DatabaseConnection;
import com.example.model.SearchCriteria;
import com.example.repository.SiteRepository;
import com.example.service.SiteSearchService;
import com.example.ui.SearchFormUI;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class to simulate the flow.
 */
public class App {
    public static void main(String[] args) throws SQLException {
        // Simulate the sequence diagram flow.

        // 1. Setup components
        DatabaseConnection dbConnection = new DatabaseConnection("jdbc:h2:mem:test", 30);
        SiteRepository repository = new SiteRepository(dbConnection);
        SiteSearchService service = new SiteSearchService(repository);
        SearchFormUI ui = new SearchFormUI();
        SiteSearchController controller = new SiteSearchController(ui, service);

        // 2. User activates site search
        System.out.println("=== User activates site search ===");
        ui.activateSiteSearch();

        // 3. User submits search request (simulated)
        System.out.println("\n=== User submits search request ===");
        Map<String, String> formData = new HashMap<>();
        formData.put("siteName", "MySite");
        formData.put("location", "London");
        ui.submitSearchRequest(formData);

        // 4. Controller processes search (simulate by directly calling controller)
        System.out.println("\n=== Controller processing search ===");
        SearchCriteria criteria = new SearchCriteria();
        criteria.setSiteName("MySite");
        criteria.setLocation("London");
        criteria.setMetadataFilters(new HashMap<>());

        SiteSearchResult result = controller.searchSite(criteria);
        System.out.println("Search completed with " + (result.hasError() ? "error: " + result.getErrorMessage() : "success."));
    }
}