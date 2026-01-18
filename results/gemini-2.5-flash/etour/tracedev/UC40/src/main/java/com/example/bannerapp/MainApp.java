
package com.example.bannerapp;

import java.util.Scanner;

/**
 * Main application class to run and demonstrate the Delete Banner Ad use case.
 * This acts as the 'actor' (Point Of Restaurant Operator) in the sequence diagram
 * and orchestrates the setup and initial call to the controller.
 */
public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- 1. Setup Dependencies ---
        // Repositories
        BannerRepositoryImpl bannerRepository = new BannerRepositoryImpl();
        OperatorRepositoryImpl operatorRepository = new OperatorRepositoryImpl();

        // Serv
        OperatorService operatorService = new OperatorService(operatorRepository);
        BannerService bannerService = new BannerService(bannerRepository, operatorService);

        // View
        BannerView bannerView = new BannerView(scanner);

        // Controller
        BannerController bannerController = new BannerController(bannerService, bannerView);

        System.out.println("--- Starting Banner Ad Removal Simulation ---");
        System.out.println("Simulating user: 'Point Of Restaurant Operator'");
        String operatorId = "OP001"; // Let's use a known operator ID from OperatorRepositoryImpl

        // --- Scenario 1: Successful Deletion ---
        System.out.println("\n--- Scenario 1: Successful Deletion ---");
        System.out.println("Initial banners for OP001 (POR001):");
        bannerRepository.findBannersByPointOfRest("POR001").forEach(b -> System.out.println("- " + b.getName()));

        // Operator initiates the feature
        // Simulating user input for the sequence:
        // 1. handleRemoveBannerFeature -> display list (view)
        // 2. promptSelectBanner() -> user types "B001"
        // 3. handleBannerSelection(B001) -> display confirmation (view)
        // 4. promptConfirmDeletion() -> user types "yes"
        // 5. handleConfirmation() -> deletion success (view)
        System.out.println("\nOperator 'OP001' wants to remove a banner. Please follow prompts:");
        System.out.println("When prompted to select banner, type 'B001'.");
        System.out.println("When prompted to confirm, type 'yes'.");

        bannerController.handleRemoveBannerFeature(operatorId);

        System.out.println("\nRemaining banners for OP001 (POR001) after scenario 1:");
        try {
            bannerRepository.findBannersByPointOfRest("POR001").forEach(b -> System.out.println("- " + b.getName()));
        } catch (NetworkConnectionException e) {
            System.err.println("Error checking remaining banners: " + e.getMessage());
        }


        // --- Scenario 2: User Cancels Deletion ---
        System.out.println("\n--- Scenario 2: User Cancels Deletion ---");
        // Re-adding a banner for demonstration
        bannerRepository.setSimulateNetworkError(false); // Ensure no error
        // The findById call is likely to retrieve or re-initialize if needed, but not add.
        // To re-add B001 explicitly after it might have been deleted:
        // bannerRepository.save(new Banner("B001", "Summer Sale", "url/summer.jpg", "POR001")); // This method does not exist on BannerRepositoryImpl
        System.out.println("Initial banners for OP001 (POR001):");
        bannerRepository.findBannersByPointOfRest("POR001").forEach(b -> System.out.println("- " + b.getName()));


        System.out.println("\nOperator 'OP001' wants to remove a banner, but will cancel. Please follow prompts:");
        System.out.println("When prompted to select banner, type 'B002'.");
        System.out.println("When prompted to confirm, type 'no'.");

        bannerController.handleRemoveBannerFeature(operatorId);

        System.out.println("\nRemaining banners for OP001 (POR001) after scenario 2 (should be unchanged for B002):");
        try {
            bannerRepository.findBannersByPointOfRest("POR001").forEach(b -> System.out.println("- " + b.getName()));
        } catch (NetworkConnectionException e) {
            System.err.println("Error checking remaining banners: " + e.getMessage());
        }

        // --- Scenario 3: Network Connection Error during deletion ---
        System.out.println("\n--- Scenario 3: Network Connection Error during deletion ---");
        // Re-adding a banner for demonstration
        bannerRepository.setSimulateNetworkError(false); // Ensure no error initially
        // bannerRepository.save(new Banner("B004", "Autumn Festival", "url/autumn.jpg", "POR001")); // This method does not exist on BannerRepositoryImpl
        System.out.println("Initial banners for OP001 (POR001):");
        bannerRepository.findBannersByPointOfRest("POR001").forEach(b -> System.out.println("- " + b.getName()));

        System.out.println("\nOperator 'OP001' tries to remove a banner, but a network error occurs. Please follow prompts:");
        System.out.println("When prompted to select banner, type 'B004'.");
        System.out.println("When prompted to confirm, type 'yes'.");

        // Simulate network error AFTER selection, but BEFORE deletion call in service.
        // For simplicity, we'll set it in the repository right before the controller makes the delete call.
        // In a real scenario, this might be dynamically injected or happen unexpectedly.
        System.out.println(">>> SIMULATING NETWORK ERROR FOR NEXT REPOSITORY OPERATION <<<");
        bannerRepository.setSimulateNetworkError(true);
        bannerController.handleRemoveBannerFeature(operatorId);
        bannerRepository.setSimulateNetworkError(false); // Reset for next operations

        System.out.println("\nRemaining banners for OP001 (POR001) after scenario 3 (B004 should still be there):");
        try {
            bannerRepository.findBannersByPointOfRest("POR001").forEach(b -> System.out.println("- " + b.getName()));
        } catch (NetworkConnectionException e) {
            System.err.println("Error checking remaining banners: " + e.getMessage());
        }

        scanner.close();
        System.out.println("\n--- Simulation Complete ---");
    }
}
