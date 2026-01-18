package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * Main class to demonstrate the system functionality.
 * This class simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Site Search System ===\n");
        
        // Create actor
        User user = new User("john_doe", Arrays.asList("SEARCH", "VIEW"));
        
        // Create UI
        SearchUI ui = new SearchUI();
        
        // Step 1: Activate search functionality
        System.out.println("1. User activates search functionality");
        ui.showForm();
        
        // Step 2: Show research form (sequence diagram message 2)
        System.out.println("\n2. UI shows research form to user");
        ui.showResearchForm();
        
        // Step 3: Fill form and submit
        System.out.println("\n3. User fills form and submits");
        SearchForm form = new SearchForm();
        form.setSiteName("Historical Museum");
        form.setLocation("New York");
        
        // Submit form through UI
        ui.submitSearchForm(form);
        
        System.out.println("\n=== Testing Network Repository ===");
        // Test with network repository
        NetworkSiteRepository netRepo = new NetworkSiteRepository();
        SearchSiteController netController = new SearchSiteController(netRepo);
        
        try {
            List<Site> netResults = netController.submitSearchForm(form);
            if (netResults != null) {
                Results netResultsDisplay = new Results();
                netResultsDisplay.displayResults(netResults);
            }
        } catch (Exception e) {
            System.out.println("Network search failed: " + e.getMessage());
        }
        
        System.out.println("\n=== Testing Invalid Form ===");
        SearchForm invalidForm = new SearchForm();
        ui.submitSearchForm(invalidForm);
        
        System.out.println("\n=== Testing User.searchSite() method ===");
        user.searchSite("test criteria");
        
        System.out.println("\n=== Testing SearchSiteController.searchSite() method ===");
        SearchSiteController testController = new SearchSiteController();
        List<Site> testResults = testController.searchSite(form);
        if (testResults != null) {
            System.out.println("Found " + testResults.size() + " sites");
        }
        
        System.out.println("\n=== System Demonstration Complete ===");
    }
}