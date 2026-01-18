package com.example.convention;

import java.util.List;

/**
 * Main application class to demonstrate the full flow of viewing convention history.
 * This class simulates the "Agency Operator" (AO) interacting with the "ConventionController".
 */
public class MainApplication {
    public static void main(String[] args) {
        // 1. Setup the dependencies
        EtourSystem etourSystem = new EtourSystem();
        ConventionRepository conventionRepository = new EtourConventionRepositoryAdapter(etourSystem);
        ConventionService conventionService = new ConventionService(conventionRepository);
        ConventionController conventionController = new ConventionController(conventionService);

        // --- Scenario 1: Successful retrieval of convention history ---
        System.out.println("--- Scenario 1: Successful Convention History Retrieval ---");
        String pointOfRestId1 = "POR-123";
        // Simulate Agency Operator action: AO -> Controller: viewConventionHistory(pointOfRestId)
        List<ConventionDTO> history1 = conventionController.viewConventionHistory(pointOfRestId1);

        if (!history1.isEmpty()) {
            System.out.println("\nAgency Operator: Displaying convention history for " + pointOfRestId1 + ":");
            history1.forEach(dto -> System.out.println("  - " + dto));
            System.out.println("Agency Operator: History displayed successfully. (REQ6, REQ8 met)");
        } else {
            System.out.println("\nAgency Operator: No convention history found or an error occurred for " + pointOfRestId1 + ".");
        }

        // --- Scenario 2: Another successful retrieval ---
        System.out.println("\n--- Scenario 2: Another Successful Convention History Retrieval ---");
        String pointOfRestId2 = "POR-456";
        List<ConventionDTO> history2 = conventionController.viewConventionHistory(pointOfRestId2);

        if (!history2.isEmpty()) {
            System.out.println("\nAgency Operator: Displaying convention history for " + pointOfRestId2 + ":");
            history2.forEach(dto -> System.out.println("  - " + dto));
            System.out.println("Agency Operator: History displayed successfully. (REQ6, REQ8 met)");
        } else {
            System.out.println("\nAgency Operator: No convention history found or an error occurred for " + pointOfRestId2 + ".");
        }

        // --- Scenario 3: Simulate ETOUR connection interruption ---
        System.out.println("\n--- Scenario 3: ETOUR Connection Interruption (REQ7) ---");
        etourSystem.setSimulateConnectionFailure(true); // Enable connection failure simulation
        String pointOfRestId3 = "POR-789"; // A different ID to ensure clean state for simulation

        // Simulate Agency Operator action, expecting an error
        List<ConventionDTO> history3 = conventionController.viewConventionHistory(pointOfRestId3);

        if (history3.isEmpty()) {
            System.out.println("\nAgency Operator: Received error: ETOUR server unavailable. Displaying empty history or error message.");
            System.out.println("Agency Operator: ETOUR connection interruption handled. (REQ7 met)");
        } else {
            System.out.println("\nAgency Operator: Unexpectedly received history despite simulated error.");
            history3.forEach(dto -> System.out.println("  - " + dto));
        }

        // Reset for any further tests if needed
        etourSystem.setSimulateConnectionFailure(false);

        // --- Scenario 4: No conventions found for a non-existent POR ---
        System.out.println("\n--- Scenario 4: No Conventions Found for Non-existent POR ---");
        String pointOfRestId4 = "NON-EXISTENT-POR";
        List<ConventionDTO> history4 = conventionController.viewConventionHistory(pointOfRestId4);

        if (history4.isEmpty()) {
            System.out.println("\nAgency Operator: Displaying empty history as no conventions found for " + pointOfRestId4 + ".");
        } else {
            System.out.println("\nAgency Operator: Unexpectedly received history for non-existent POR.");
            history4.forEach(dto -> System.out.println("  - " + dto));
        }

        System.out.println("\n--- End of Demonstration ---");
    }
}