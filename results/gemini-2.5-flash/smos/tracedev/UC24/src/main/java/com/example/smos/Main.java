package com.example.smos;

import java.util.Scanner;

/**
 * Main application class to wire up dependencies and demonstrate the interactions
 * defined in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup: Wiring up the dependencies ---
        System.out.println("--- Application Initialization ---");

        // Data Access Layer
        SMOSDatabase smosDatabase = new SMOSDatabase(false); // Initially no connection failure
        ITeachingRepository teachingRepository = new TeachingRepository(smosDatabase);

        // Application Layer
        TeachingService teachingService = new TeachingService(teachingRepository);

        // Presentation Layer
        TeachingDetailView teachingDetailView = new TeachingDetailView();
        TeachingDetailController teachingDetailController = new TeachingDetailController(teachingService, teachingDetailView);

        // Placeholder for TeachingList components (as per Class Diagram)
        TeachingListView teachingListView = new TeachingListView();
        TeachingListController teachingListController = new TeachingListController(teachingListView, teachingDetailController);

        System.out.println("--- Application Ready ---");

        Scanner scanner = new Scanner(System.in);
        String teachingId;

        // --- Scenario 1: Successful retrieval of teaching details ---
        System.out.println("\n--- Scenario 1: Successful Teaching Details Retrieval ---");
        System.out.print("Administrator (Actor): Enter Teaching ID to view details (e.g., T001 or T002): ");
        teachingId = scanner.nextLine();
        // Simulate Administrator interaction: clicks "Teaching details" button
        // View -> Controller : requestTeachingDetails(selectedTeachingId)
        teachingDetailController.requestTeachingDetails(teachingId);

        // --- Scenario 2: Teaching Not Found ---
        System.out.println("\n--- Scenario 2: Teaching Not Found ---");
        System.out.print("Administrator (Actor): Enter a non-existent Teaching ID (e.g., T999): ");
        teachingId = scanner.nextLine();
        teachingDetailController.requestTeachingDetails(teachingId);

        // --- Scenario 3: Simulate SMOS Server Connection Interruption (Alternative Path) ---
        System.out.println("\n--- Scenario 3: SMOS Server Connection Interruption ---");
        smosDatabase.setSimulateConnectionFailure(true); // Activate connection failure simulation
        System.out.print("Administrator (Actor): Enter Teaching ID (e.g., T001) while connection is interrupted: ");
        teachingId = scanner.nextLine();
        // View -> Controller : requestTeachingDetails(selectedTeachingId)
        teachingDetailController.requestTeachingDetails(teachingId);
        smosDatabase.setSimulateConnectionFailure(false); // Reset for other scenarios if any

        // --- Scenario 4: Demonstrate TeachingListController navigation (placeholder) ---
        System.out.println("\n--- Scenario 4: Demonstrating TeachingListController (Placeholder) ---");
        teachingListController.displayTeachingList(); // Show the list
        System.out.print("Administrator (Actor): Enter Teaching ID from list to navigate to details (e.g., T002): ");
        teachingId = scanner.nextLine();
        teachingListController.navigateToTeachingDetails(teachingId);

        scanner.close();
        System.out.println("\n--- Application Shutdown ---");
    }
}