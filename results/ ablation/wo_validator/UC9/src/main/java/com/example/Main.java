package com.example;

import com.example.controller.SearchController;
import com.example.model.User;
import com.example.repository.PointOfRestRepository;
import com.example.repository.PointOfRestRepositoryImpl;
import com.example.server.ServerConnection;
import com.example.ui.SearchFormUI;
import com.example.validation.SearchValidator;
import java.util.Map;

/**
 * Main application class to demonstrate the runnable system.
 * Implements the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting search system simulation...");

        // Create components as per class diagram relationships
        ServerConnection serverConnection = new ServerConnection("http://example-server.com");
        PointOfRestRepository repository = new PointOfRestRepositoryImpl(serverConnection);
        SearchValidator validator = new SearchValidator();
        SearchController controller = new SearchController(repository, validator);

        SearchFormUI ui = new SearchFormUI();
        ui.setSearchController(controller);

        User user = new User("user123");
        user.fillSearchForm(Map.of("location", "Highway 1", "facility", "Parking"));

        System.out.println("\n--- Executing full search flow ---");
        user.performSearch(ui);

        System.out.println("\n--- Direct controller test ---");
        try {
            var results = controller.handleSearchRequest(Map.of("test", "data"));
            System.out.println("Search returned " + results.size() + " results.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("\nSimulation completed.");
    }
}