package com.example.agencyapp;

import com.example.agencyapp.application.RefreshmentPointDeletionService;
import com.example.agencyapp.domain.RefreshmentPoint;
import com.example.agencyapp.infrastructure.RefreshmentPointRepositoryImpl;
import com.example.agencyapp.presentation.AgencyOperatorUI;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class to demonstrate the functionality of the Delete Refreshment Point use case.
 * This acts as the entry point and simulates the "Agency Operator" interacting with the UI.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup: Instantiate components ---
        // Infrastructure Layer
        RefreshmentPointRepositoryImpl repository = new RefreshmentPointRepositoryImpl();

        // Application Layer
        RefreshmentPointDeletionService service = new RefreshmentPointDeletionService(repository);

        // Presentation Layer
        AgencyOperatorUI ui = new AgencyOperatorUI(service);

        System.out.println("--- Starting Refreshment Point Deletion Demonstration ---");

        // Entry Condition: Agency Operator is logged in.
        // Pre-condition: Refreshment points are displayed.
        // We retrieve points from the repository and display them.
        List<RefreshmentPoint> allPoints = repository.getAllRefreshmentPoints().values().stream().collect(Collectors.toList());
        ui.displayRefreshmentPoints(allPoints);

        // --- Scenario 1: Successful Deletion (AO confirms, service succeeds) ---
        System.out.println("\n--- SCENARIO 1: Successful Deletion (ID: RP001) ---");
        ui.selectRefreshmentPoint("RP001"); // AO selects a point (REQ-005)
        ui.activateDeletionFunction(); // AO activates deletion

        allPoints = repository.getAllRefreshmentPoints().values().stream().collect(Collectors.toList());
        ui.displayRefreshmentPoints(allPoints); // Show updated list

        // --- Scenario 2: Deletion Cancelled by Operator ---
        System.out.println("\n--- SCENARIO 2: Deletion Cancelled (ID: RP002) ---");
        ui.selectRefreshmentPoint("RP002"); // AO selects another point
        // For this scenario, the user input for showConfirmationDialog should be 'no'.
        // The console will prompt the user.
        ui.activateDeletionFunction();

        allPoints = repository.getAllRefreshmentPoints().values().stream().collect(Collectors.toList());
        ui.displayRefreshmentPoints(allPoints); // Show updated list

        // --- Scenario 3: Deletion Fails (Repository returns false, e.g., not found or generic error) ---
        System.out.println("\n--- SCENARIO 3: Deletion Fails (ID: fail_id, AO confirms) ---");
        ui.selectRefreshmentPoint("fail_id"); // AO selects an ID that will cause repo to return false
        // For this scenario, the user input for showConfirmationDialog should be 'yes'.
        // The console will prompt the user.
        ui.activateDeletionFunction();

        allPoints = repository.getAllRefreshmentPoints().values().stream().collect(Collectors.toList());
        ui.displayRefreshmentPoints(allPoints); // Show updated list

        // --- Scenario 4: Deletion Fails due to ETOUR Connection Exception (REQ-012) ---
        System.out.println("\n--- SCENARIO 4: Deletion Fails due to ETOUR Connection Exception (ID: etour_error_id, AO confirms) ---");
        ui.selectRefreshmentPoint("etour_error_id"); // AO selects an ID that will cause repo to throw ETOURConnectionException
        // For this scenario, the user input for showConfirmationDialog should be 'yes'.
        // The console will prompt the user.
        ui.activateDeletionFunction();

        allPoints = repository.getAllRefreshmentPoints().values().stream().collect(Collectors.toList());
        ui.displayRefreshmentPoints(allPoints); // Show updated list

        System.out.println("\n--- End of Demonstration ---");
    }
}