package com.example;

import com.example.actor.AgencyOperator;
import com.example.controller.DeleteRefreshmentPointController;
import com.example.controller.SearchCulturalHeritageController;
import com.example.error.ErrorHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to demonstrate the runnable system.
 * Simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // Setup actors and components
        AgencyOperator operator = new AgencyOperator("OP001");
        ErrorHandler errorHandler = new ErrorHandler();
        SearchCulturalHeritageController searchController = new SearchCulturalHeritageController();
        DeleteRefreshmentPointController controller = new DeleteRefreshmentPointController(
                operator, searchController, errorHandler);

        System.out.println("=== Precondition: View Refreshment Points List ===");
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("location", "Main Square");
        controller.retrieveRefreshmentPointList(criteria).forEach(System.out::println);

        System.out.println("\n=== Main Success Scenario ===");
        // Simulate operator selecting ID 1
        controller.activateDeleteFunction(1);
        controller.handleDeleteRequest(1);

        System.out.println("\n=== Alternative Flow: Connection Interruption ===");
        // Simulate connection loss
        com.example.data.DataSource dataSource = new com.example.data.DataSource();
        dataSource.setConnected(false);
        // This would trigger connection error in repository
        controller.handleConnectionError(new RuntimeException("ETOUR connection interruption"));
    }
}