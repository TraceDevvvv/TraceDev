package com.example.banner;

import java.util.Scanner;

/**
 * ModifyBannerApp.java
 * Main application class that simulates the ModifyBanner use case flow.
 * This class demonstrates the steps for a restaurant operator to change a banner image.
 * It includes authentication simulation, listing banners, selecting a banner,
 * image validation, confirmation, and updating.
 * It also handles cancellation and connection interruption scenarios.
 */
public class ModifyBannerApp {
    // Simulate authentication status
    private boolean authenticated = false;
    // Scanner for user input
    private Scanner scanner = new Scanner(System.in);
    // Banner manager instance
    private BannerManager bannerManager = BannerManager.getInstance();
    // Image validator instance
    private ImageValidator imageValidator = new ImageValidator();

    /**
     * Main entry point of the application.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        ModifyBannerApp app = new ModifyBannerApp();
        app.run();
    }

    /**
     * Runs the main use case flow.
     */
    private void run() {
        System.out.println("=== Modify Banner Use Case Simulation ===");
        // Step: Authentication (simulated)
        if (!authenticate()) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        // Step: Select editing functionality (implied by choosing to modify banner)
        System.out.println("\n--- Editing functionality selected ---");
        // Step: View list of banners
        listBanners();

        // Step: Select a banner from the list
        Banner selectedBanner = selectBanner();
        if (selectedBanner == null) {
            System.out.println("No banner selected. Operation cancelled.");
            return;
        }

        // Step: Display form for image selection (simulated by entering image path)
        System.out.println("\n--- Image Selection Form ---");
        System.out.println("Selected Banner: " + selectedBanner);
        String newImagePath = enterImagePath();
        if (newImagePath == null) {
            System.out.println("Operation cancelled by user.");
            return;
        }

        // Step: Validate image characteristics
        boolean isValid = validateImage(newImagePath);
        if (!isValid) {
            // Enable use case Errored (simulated by printing error and exiting)
            System.out.println("Image validation failed. Enabling Errored use case.");
            // In a real system, would trigger error handling flow
            return;
        }

        // Step: Ask for confirmation
        if (!confirmChange()) {
            System.out.println("Change cancelled by user.");
            return;
        }

        // Step: Update banner image
        boolean success = updateBannerImage(selectedBanner.getId(), newImagePath);
        if (success) {
            System.out.println("\n=== Banner image updated successfully! ===");
        } else {
            System.out.println("Failed to update banner image.");
        }

        // Simulate server connection interruption scenario
        simulateConnectionInterruption();

        scanner.close();
    }

    /**
     * Simulates authentication of the restaurant operator.
     * @return true if authentication successful, false otherwise
     */
    private boolean authenticate() {
        System.out.println("\n--- Authentication ---");
        System.out.print("Enter operator username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simulated authentication logic (for demonstration, accept any non-empty)
        if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            authenticated = true;
            System.out.println("Authentication successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    /**
     * Displays the list of banners associated with the restaurant point.
     */
    private void listBanners() {
        System.out.println("\n--- Banner List ---");
        bannerManager.displayBanners();
    }

    /**
     * Prompts the user to select a banner by ID.
     * @return the selected Banner object, or null if invalid selection or cancellation
     */
    private Banner selectBanner() {
        System.out.print("\nEnter the ID of the banner to edit (or 'cancel' to abort): ");
        String input = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(input)) {
            return null;
        }
        Banner banner = bannerManager.getBannerById(input);
        if (banner == null) {
            System.out.println("Invalid banner ID. Please try again.");
            // Recursive retry (simple implementation)
            return selectBanner();
        }
        return banner;
    }

    /**
     * Prompts the user to enter a new image path.
     * @return the entered image path, or null if cancelled
     */
    private String enterImagePath() {
        System.out.print("Enter the new image file path (or 'cancel' to abort): ");
        String path = scanner.nextLine();
        if ("cancel".equalsIgnoreCase(path)) {
            return null;
        }
        return path;
    }

    /**
     * Validates the image using ImageValidator.
     * For simulation, we assume dummy file size and dimensions.
     * @param imagePath the image path to validate
     * @return true if valid, false otherwise
     */
    private boolean validateImage(String imagePath) {
        System.out.println("\n--- Validating Image ---");
        try {
            // Simulated file size and dimensions (in real app, these would be extracted from the image)
            long fileSize = 1024 * 1024; // 1 MB
            int width = 800;
            int height = 600;
            imageValidator.validate(imagePath, fileSize, width, height);
            System.out.println("Image validation passed.");
            return true;
        } catch (ImageValidator.ImageValidationException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Asks the user for confirmation before changing the banner.
     * @return true if confirmed, false otherwise
     */
    private boolean confirmChange() {
        System.out.print("\nConfirm change of banner image? (yes/no): ");
        String answer = scanner.nextLine();
        return "yes".equalsIgnoreCase(answer);
    }

    /**
     * Updates the banner image via BannerManager.
     * @param bannerId the ID of the banner to update
     * @param newImagePath the new image path
     * @return true if update successful, false otherwise
     */
    private boolean updateBannerImage(String bannerId, String newImagePath) {
        System.out.println("\n--- Updating Banner ---");
        try {
            return bannerManager.updateBannerImage(bannerId, newImagePath);
        } catch (IllegalArgumentException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Simulates a connection interruption to the server ETOUR.
     * This is a placeholder for handling such scenarios.
     */
    private void simulateConnectionInterruption() {
        System.out.println("\n--- Simulating connection interruption ---");
        System.out.println("Connection to server ETOUR lost. Operation may be incomplete.");
        // In a real system, would trigger rollback or recovery mechanisms
    }
}
