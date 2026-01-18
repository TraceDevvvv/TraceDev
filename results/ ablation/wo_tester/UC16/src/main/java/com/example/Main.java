package com.example;

import com.example.domain.AgencyOperator;
import com.example.domain.Tourist;
import com.example.controllers.DeleteTouristController;
import com.example.controllers.SearchTouristController;
import com.example.infrastructure.DataSource;
import com.example.repository.TouristRepository;
import com.example.repository.TouristRepositoryImpl;

import java.util.List;

/**
 * Main class to simulate the sequence diagram flow.
 * This is a runnable test of the system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup infrastructure
        DataSource dataSource = new DataSource("jdbc:h2:mem:test", "sa", "");
        TouristRepository repository = new TouristRepositoryImpl(dataSource);
        AgencyOperator operator = new AgencyOperator("john_doe", "password");

        // Create controllers
        SearchTouristController searchController = new SearchTouristController(repository);
        DeleteTouristController deleteController = new DeleteTouristController(repository, operator);

        // Simulate the sequence diagram steps

        // 1. Operator logs in (implicit in sequence, but necessary for validation)
        operator.login();
        System.out.println("Operator logged in: " + operator.isLoggedIn());

        // 2. Search for tourists
        String criteria = "John";
        System.out.println("Searching tourists with criteria: " + criteria);
        List<Tourist> tourists = searchController.searchTourist(criteria);
        System.out.println("Found " + tourists.size() + " tourist(s)");

        // 3. Select a tourist (simulate by picking first found)
        if (!tourists.isEmpty()) {
            String selectedId = tourists.get(0).getId();
            // In sequence diagram, AO calls selectTourist which triggers STC to find by ID.
            Tourist selected = searchController.findTouristById(selectedId);
            System.out.println("Selected tourist: " + selected);

            // 4. Execute deletion flow
            System.out.println("\n--- Starting deletion flow ---");
            boolean deletionSuccess = deleteController.executeDeletionFlow(selectedId);
            System.out.println("Deletion flow result: " + deletionSuccess);
        } else {
            System.out.println("No tourists found, skipping deletion.");
        }

        // 5. Operator logs out
        operator.logout();
        System.out.println("\nOperator logged out. Simulation complete.");
    }
}