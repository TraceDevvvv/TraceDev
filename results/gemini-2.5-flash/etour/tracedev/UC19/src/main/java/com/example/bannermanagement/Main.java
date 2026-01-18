package com.example.bannermanagement;

import java.util.Scanner;

/**
 * Main application class to demonstrate the Delete Banner Ad use case
 * as described in the Class and Sequence Diagrams.
 * This class orchestrates the creation of objects and simulates user interactions.
 */
public class Main {

    private static Scanner appScanner = new Scanner(System.in);

    public static void main(String[] args) {
        // --- 1. Initialize core components ---
        AuthenticationService authService = new AuthenticationService();
        IRefreshmentPointRepository rpRepo = new RefreshmentPointRepository();
        IBannerRepository bannerRepo = new BannerRepository();
        BannerManagementService bannerService = new BannerManagementService(rpRepo, bannerRepo);
        AgencyOperatorController controller = new AgencyOperatorController(bannerService, authService);
        AgencyOperatorView view = new AgencyOperatorView(controller);

        System.out.println("--- Starting Banner Management Application Simulation ---");

        // --- Simulate Authentication Check (REQ-002) ---
        System.out.println("\n--- Step 0: Initial Authentication Check ---");
        // Simulate an unauthenticated state first
        authService.logout(); // Ensure not logged in initially
        view.attemptOperation(); // This will trigger the checkAuthentication() flow
        if (!authService.isLoggedIn()) {
            System.out.println("\nSystem detected user is not logged in. Attempting to log in...");
            // Simulate login process (successful for this path)
            if (authService.login("agency", "pass")) {
                System.out.println("Login successful. Proceeding with the use case.");
            } else {
                System.out.println("Login failed. Exiting application.");
                appScanner.close();
                return;
            }
        }

        // --- Simulate Successful Path of Sequence Diagram ---

        // AO -> View: initDisplayRefreshmentPoints()
        view.initDisplayRefreshmentPoints(); // View calls Controller.showRefreshmentPoints()

        // AO -> View: selectRefreshmentPoint(rpId : String)
        // Simulate user input for selecting a refreshment point. Using "RP001" for the flow.
        String selectedRpId = "RP001";
        System.out.println("Simulating user selection: " + selectedRpId);
        view.selectRefreshmentPoint(selectedRpId);

        // AO -> View: accessBannerRemovalFunction()
        view.accessBannerRemovalFunction(); // View calls Controller.accessBannerRemovalFunction()

        // AO -> View: selectBanner(bannerId : String) (Step 5)
        // Simulate user input for selecting a banner to delete. Using "B001" for the flow.
        String selectedBannerId = "B001";
        System.out.println("Simulating user selection to delete banner: " + selectedBannerId);
        view.selectBanner(selectedBannerId);

        // AO -> View: clickDeleteButton() (Step 6)
        view.clickDeleteButton(); // View calls Controller.confirmBannerDeletionRequest()

        // AO -> View: confirmOperation(true) (Step 8)
        // Simulate user confirming deletion.
        System.out.println("Simulating user confirmation: yes");
        view.confirmOperation(true); // View calls Controller.performBannerDeletion(true)

        System.out.println("\n--- Successful path demonstration complete ---");

        // --- Demonstrate ETOUR Connection Error Paths (REQ-004) ---
        System.out.println("\n--- Demonstrating ETOUR Connection Errors ---");

        // Simulate error during getAvailableRefreshmentPoints()
        System.out.println("\n--- Scenario: Error fetching Refreshment Points ---");
        RefreshmentPointRepository.simulateConnectionError = true;
        view.initDisplayRefreshmentPoints();
        RefreshmentPointRepository.simulateConnectionError = false; // Reset for next ops

        // Simulate error during getBannersForRefreshmentPoint()
        System.out.println("\n--- Scenario: Error fetching Banners for RP001 ---");
        view.selectRefreshmentPoint("RP001"); // Select RP001 again
        BannerRepository.simulateConnectionError = true;
        view.accessBannerRemovalFunction();
        BannerRepository.simulateConnectionError = false; // Reset for next ops

        // Simulate error during deleteBanner()
        System.out.println("\n--- Scenario: Error during Banner Deletion (B003) ---");
        view.selectRefreshmentPoint("RP002"); // Select RP002
        view.accessBannerRemovalFunction(); // Get banners for RP002
        view.selectBanner("B003"); // Select B003
        view.clickDeleteButton();
        BannerRepository.simulateConnectionError = true;
        System.out.println("Simulating user confirmation: yes");
        view.confirmOperation(true);
        BannerRepository.simulateConnectionError = false; // Reset for next ops


        System.out.println("\n--- ETOUR error demonstration complete ---");

        // Verify state after deletion
        System.out.println("\n--- Verifying state after operations ---");
        view.initDisplayRefreshmentPoints(); // Display all points
        view.selectRefreshmentPoint("RP001");
        view.accessBannerRemovalFunction(); // B001 should be gone
        view.selectRefreshmentPoint("RP002");
        view.accessBannerRemovalFunction(); // B003 should still be there if deletion failed.

        view.closeScanner();
        appScanner.close();
        System.out.println("\n--- Application Simulation Ended ---");
    }
}