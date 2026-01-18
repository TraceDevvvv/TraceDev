package com.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class to run the application and simulate the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // Create entities
        User user = new User("user123", true);
        AuthenticationService authService = new AuthenticationService();
        // Verify authentication (entry condition)
        boolean authConfirmed = authService.verifyAuthentication(user);
        System.out.println("Authentication confirmed: " + authConfirmed);
        if (!authConfirmed) {
            System.out.println("User not authenticated. Exiting.");
            return;
        }

        // Create external server (initially connected)
        ETOURServer etourServer = new ETOURServer(true);
        PointOfRestRepository repository = new PointOfRestRepositoryImpl(etourServer);
        SearchService searchService = new SearchService(repository);
        SearchController controller = new SearchController(authService, searchService);

        // Simulate sequence diagram steps
        controller.activateSearch();
        System.out.println(controller.showSearchForm());

        // User submits criteria
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("name", "Alpha"); // Search for points containing "Alpha"
        controller.submitSearchCriteria(criteria);

        // Simulate connection interruption scenario
        System.out.println("\n--- Simulating connection interruption ---");
        etourServer.setConnected(false); // Break connection
        controller.submitSearchCriteria(criteria);
    }
}