package com.example;

import com.example.adapter.in.web.SearchController;
import com.example.adapter.out.persistence.DatabaseCulturalObjectRepository;
import com.example.adapter.out.web.WebSearchFormRenderer;
import com.example.application.service.SearchService;
import com.example.domain.SearchCriteria;
import com.example.ui.SearchResult;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class demonstrating the search functionality.
 * This class simulates the user interactions and system flows.
 */
public class MainApplication {
    public static void main(String[] args) {
        System.out.println("=== Cultural Object Search System ===");
        
        // Initialize components (dependency injection)
        DatabaseCulturalObjectRepository repository = new DatabaseCulturalObjectRepository();
        SearchService searchService = new SearchService(repository);
        WebSearchFormRenderer formRenderer = new WebSearchFormRenderer();
        SearchController controller = new SearchController(searchService, formRenderer);
        
        // Simulate Flow of Events 1: User activates search functionality
        System.out.println("\n1. User activates search functionality:");
        controller.activateSearchFunctionality();
        
        // Simulate form rendering
        System.out.println("\n2. System displays search form...");
        
        // Simulate Flow of Events 4: User submits search form
        System.out.println("\n3. User submits search form with criteria:");
        
        // Create sample form data
        Map<String, String> formData = new HashMap<>();
        formData.put("title", "Mona");
        formData.put("creator", "Leonardo");
        formData.put("objectType", "Painting");
        
        // Process search
        System.out.println("\n4. System processes the request:");
        SearchResult result = controller.submitSearchForm(formData);
        
        // Display results
        System.out.println("\n5. Search Results:");
        System.out.println("Search time: " + result.getSearchTime() + "ms");
        System.out.println("Within time limit (5000ms): " + result.isWithinTimeLimit());
        
        // Test with empty criteria
        System.out.println("\n6. Testing with empty criteria:");
        Map<String, String> emptyFormData = new HashMap<>();
        SearchResult emptyResult = controller.submitSearchForm(emptyFormData);
        
        // Test alternative flow: connection interruption simulation
        System.out.println("\n7. Testing connection interruption scenario:");
        testConnectionInterruption(searchService);
        
        System.out.println("\n=== Application Complete ===");
    }
    
    /**
     * Tests the connection interruption scenario.
     * This runs multiple searches to potentially trigger the simulated connection failure.
     */
    private static void testConnectionInterruption(SearchService searchService) {
        System.out.println("Running multiple searches (10% chance of connection failure each time)...");
        
        SearchCriteria criteria = new SearchCriteria();
        criteria.setTitle("Test");
        
        for (int i = 1; i <= 5; i++) {
            System.out.print("Search attempt " + i + ": ");
            try {
                searchService.execute(criteria);
                System.out.println("Success");
            } catch (Exception e) {
                System.out.println("Failed - " + e.getMessage());
                if (e.getCause() != null) {
                    System.out.println("  Caused by: " + e.getCause().getMessage());
                }
            }
        }
    }
}