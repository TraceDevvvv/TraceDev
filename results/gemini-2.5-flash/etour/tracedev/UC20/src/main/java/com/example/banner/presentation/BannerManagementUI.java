
package com.example.banner.presentation;

import com.example.banner.application.BannerInsertionResult;
import com.example.banner.application.BannerService;
import com.example.banner.domain.PointOfRest;
import com.example.banner.infrastructure.ETOURConnectionException;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Base64; // For simulating image data

/**
 * The BannerManagementUI class represents the user interface for managing banners.
 * It interacts with the {@link BannerService} to perform operations and
 * displays information to the user (simulated via console output).
 *
 * Authentication is assumed to be handled externally (REQ-EntryCondition-001).
 */
public class BannerManagementUI {
    private final BannerService bannerService;
    private final Scanner scanner; // For simulating user input

    // State variables for multi-step operations
    private String selectedPointOfRestId;
    private byte[] selectedImageData;

    /**
     * Constructs a BannerManagementUI with a BannerService dependency.
     *
     * @param bannerService The application service layer for banner operations.
     */
    public BannerManagementUI(BannerService bannerService) {
        this.bannerService = bannerService;
        this.scanner = new Scanner(System.in);
        this.selectedPointOfRestId = null;
        this.selectedImageData = null;
    }

    /**
     * Displays a list of available Points of Rest to the user.
     *
     * @param points A list of {@link PointOfRest} objects.
     */
    public void displayPointsOfRest(List<PointOfRest> points) {
        System.out.println("\n--- Available Points of Rest ---");
        if (points.isEmpty()) {
            System.out.println("No points of rest available.");
            return;
        }
        for (PointOfRest point : points) {
            System.out.println("ID: " + point.getId() + ", Name: " + point.getName() +
                               ", Banners: " + point.getCurrentBannerCount() + "/" + point.getMaxBanners());
        }
        System.out.println("---------------------------------");
    }

    /**
     * Displays an empty form for inserting a new banner.
     */
    public void displayBannerForm() {
        System.out.println("\n--- Insert New Banner Form ---");
        System.out.println("Please select a Point of Rest and provide image data.");
        System.out.println("------------------------------");
    }

    /**
     * Displays a confirmation dialog to the user.
     */
    public void displayConfirmationDialog() {
        System.out.println("\n--- Confirm Banner Insertion ---");
        System.out.println("Are you sure you want to insert this banner?");
        System.out.println("Type 'yes' to confirm, or 'no' to cancel.");
        System.out.print("> ");
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to display.
     */
    public void displaySuccess(String message) { // SD: m56
        System.out.println("\n[SUCCESS] " + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayError(String message) { // SD: m29, m39
        System.err.println("\n[ERROR] " + message);
    }

    /**
     * Displays a status message to the user.
     *
     * @param message The status message to display.
     */
    public void displayStatus(String message) {
        System.out.println("\n[STATUS] " + message);
    }

    /**
     * Requests and displays available points of rest.
     * Simulates the initial UI interaction.
     */
    public void requestPointsOfRest() {
        System.out.println("\nAgency Operator: requestPointsOfRest()");
        List<PointOfRest> points = bannerService.getAvailablePointsOfRest();
        displayPointsOfRest(points);
    }

    /**
     * Simulates selecting a point of rest by its ID.
     *
     * @param id The ID of the selected point of rest.
     */
    public void selectPointOfRest(String id) {
        this.selectedPointOfRestId = id;
        System.out.println("UI: Point of Rest selected: " + id);
    }

    /**
     * Simulates requesting the banner insertion form.
     */
    public void requestInsertBannerForm() {
        System.out.println("Agency Operator: requestInsertBannerForm()");
        displayBannerForm();
    }

    /**
     * Simulates selecting an image for the banner.
     *
     * @param imageData The raw binary data of the image.
     */
    public void selectImage(byte[] imageData) {
        this.selectedImageData = imageData;
        System.out.println("UI: Image selected (size: " + (imageData != null ? imageData.length : 0) + " bytes)");
    }

    /**
     * Submits the banner request to the service layer.
     * This method handles the primary flow of banner insertion, including validation
     * and managing the confirmation step.
     *
     * @param pointOfRestId The ID of the selected point of rest.
     * @param imageData The raw binary data of the selected image.
     */
    public void submitBannerRequest(String pointOfRestId, byte[] imageData) { // SD: m27, m37, m41
        System.out.println("\nAgency Operator: submitBannerRequest(" + pointOfRestId + ", [imageData])");

        BannerInsertionResult result = bannerService.insertBanner(pointOfRestId, imageData);

        switch (result) {
            case INVALID_IMAGE:
                displayError("Invalid image characteristics. Please ensure the image meets all requirements.");
                break;
            case MAX_BANNERS_EXCEEDED:
                displayError("Maximum banners reached for this point of rest. Cannot add new banner.");
                break;
            case AWAITING_CONFIRMATION:
                // If awaiting confirmation, the UI should then display a confirmation dialog
                // and wait for user input. This is handled by a subsequent call.
                displayConfirmationDialog();
                break;
            case SUCCESS: // This case should not be reached directly from insertBanner based on SD
            case CANCELED:
            case ETOUR_CONNECTION_ERROR:
            default:
                // Handle unexpected results or cases that should be handled by confirmBannerInsertion
                System.err.println("UI: Unexpected BannerInsertionResult: " + result);
                displayError("An unexpected error occurred during banner submission.");
                break;
        }
    }

    /**
     * Simulates the Agency Operator confirming the banner insertion.
     * This method is called after `displayConfirmationDialog()` and proceeds
     * with the actual insertion via the service layer.
     */
    public void confirmBannerInsertion() { // SD: m56, m58
        System.out.println("\nAgency Operator: confirmBannerInsertion()");
        if (selectedPointOfRestId == null || selectedImageData == null) {
            displayError("Cannot confirm: Point of Rest or Image Data not selected.");
            return;
        }

        try {
            bannerService.confirmBannerInsertion(selectedPointOfRestId, selectedImageData);
            displaySuccess("New banner inserted to selected Refreshments.");
        } catch (ETOURConnectionException e) {
            displayError("Connection to ETOUR server interrupted during banner operation: " + e.getMessage());
        } finally {
            // Clear state after operation
            clearPendingState();
        }
    }

    /**
     * Simulates the Agency Operator canceling the banner insertion.
     */
    public void cancelBannerInsertion() {
        System.out.println("\nAgency Operator: cancelBannerInsertion()");
        displayStatus("Operation cancelled by Agency Operator.");
        clearPendingState(); // Clear any pending state
    }

    /**
     * Clears the internally stored state for pending banner insertion.
     */
    private void clearPendingState() {
        this.selectedPointOfRestId = null;
        this.selectedImageData = null;
    }

    /**
     * Main method to demonstrate the UI flow.
     * This simulates the "Agency Operator" interactions.
     */
    public void runDemo() {
        System.out.println("--- Starting Banner Management UI Demo ---");

        // Precondition: Agency Operator is logged in. (REQ-EntryCondition-001)
        System.out.println("UI: Agency Operator successfully logged in.");

        // Step 1: Request and display points of rest
        requestPointsOfRest();

        // Simulate AO selecting a point of rest (ID: "por1" from dummy data)
        String chosenPointOfRestId = "por1"; // Assuming a valid ID from the displayed list
        System.out.println("\n--- Agency Operator (AO) Actions ---");
        selectPointOfRest(chosenPointOfRestId); // Simulate AO selection

        // Step 2 & 3: AO requests insert banner form
        requestInsertBannerForm();

        // Step 4: UI displays form (already called by requestInsertBannerForm)

        // Step 5: AO selects an image
        // Simulate image data (e.g., a small JPEG, Base64 decoded)
        // This is a placeholder for actual image file selection
        byte[] validImageData = Base64.getDecoder().decode("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=");
        selectImage(validImageData);

        // --- Scenario 1: Successful Insertion ---
        System.out.println("\n--- Scenario 1: Successful Insertion ---");
        submitBannerRequest(chosenPointOfRestId, validImageData);
        // UI receives AWAITING_CONFIRMATION and displays dialog. AO then confirms.
        String confirmationChoice = "yes"; // Simulate user input
        if ("yes".equalsIgnoreCase(confirmationChoice)) {
            confirmBannerInsertion();
        } else {
            cancelBannerInsertion();
        }

        // --- Scenario 2: Invalid Image ---
        System.out.println("\n--- Scenario 2: Invalid Image (empty data) ---");
        requestInsertBannerForm();
        byte[] invalidImageData = new byte[]{}; // Empty image data
        selectImage(invalidImageData);
        submitBannerRequest(chosenPointOfRestId, invalidImageData); // Should return INVALID_IMAGE

        // --- Scenario 3: Max Banners Exceeded (requires setup) ---
        // Let's assume chosenPointOfRestId "por2" has max 1 banner, and one is already there from dummy data
        String fullPointOfRestId = "por2";
        PointOfRest por2 = bannerService.getAvailablePointsOfRest().stream()
                               .filter(p -> p.getId().equals(fullPointOfRestId))
                               .findFirst().orElse(null);
        if (por2 != null && !por2.canAddBanner()) {
            System.out.println("\n--- Scenario 3: Max Banners Exceeded ---");
            requestInsertBannerForm();
            selectPointOfRest(fullPointOfRestId);
            selectImage(validImageData);
            submitBannerRequest(fullPointOfRestId, validImageData); // Should return MAX_BANNERS_EXCEEDED
        } else {
            System.out.println("\n--- Skipping Scenario 3: Max Banners Exceeded (PointOfRest 'por2' not correctly set up as full) ---");
            System.out.println("  Current state of 'por2': " + por2);
        }

        // --- Scenario 4: ETOUR Connection Error during save ---
        // To force an ETOUR error, we might need to repeatedly call save/update or have a specific mock setup.
        // The in-memory repositories simulate this via a counter. Let's make a few successful calls first
        // to reach the error threshold for the next operation.
        System.out.println("\n--- Scenario 4: ETOUR Connection Error during save (will happen after some operations) ---");
        String etourTestPointId = "por3"; // Use a point of rest that is not full
        PointOfRest por3 = bannerService.getAvailablePointsOfRest().stream()
                               .filter(p -> p.getId().equals(etourTestPointId))
                               .findFirst().orElse(null);
        if (por3 != null) {
            for (int i = 0; i < 5; i++) { // Call enough times to trigger error counter in repo
                if (!por3.canAddBanner()) {
                    System.out.println("Point of Rest " + por3.getName() + " is full. Cannot test ETOUR error on save.");
                    break;
                }
                System.out.println("\nAttempt " + (i + 1) + " to insert banner for ETOUR error test...");
                selectPointOfRest(etourTestPointId);
                selectImage(validImageData);
                submitBannerRequest(etourTestPointId, validImageData);
                if (this.selectedPointOfRestId != null && this.selectedImageData != null) {
                    // Only confirm if it's awaiting confirmation
                    try {
                        bannerService.confirmBannerInsertion(this.selectedPointOfRestId, this.selectedImageData);
                        displaySuccess("Banner successfully inserted for ETOUR test attempt " + (i + 1));
                    } catch (ETOURConnectionException e) {
                        displayError("ETOUR connection error caught in UI (as expected): " + e.getMessage());
                        break; // Stop after first error
                    } finally {
                        clearPendingState();
                    }
                } else {
                    System.out.println("UI: Not awaiting confirmation after attempt " + (i+1) + ", check previous output for error.");
                    // Check for error messages from submitBannerRequest here if not awaiting confirmation
                    break; // Likely an error like Max Banners Exceeded
                }
                por3 = bannerService.getAvailablePointsOfRest().stream()
                               .filter(p -> p.getId().equals(etourTestPointId))
                               .findFirst().orElse(null); // Refresh POR state
            }
        } else {
            System.out.println("--- Skipping Scenario 4: ETOUR Connection Error (PointOfRest 'por3' not found) ---");
        }


        // --- Scenario 5: User cancels insertion ---
        System.out.println("\n--- Scenario 5: User cancels insertion ---");
        requestInsertBannerForm();
        selectPointOfRest(chosenPointOfRestId); // Re-use a valid POR
        selectImage(validImageData);
        submitBannerRequest(chosenPointOfRestId, validImageData);
        // UI receives AWAITING_CONFIRMATION, displays dialog. AO then cancels.
        confirmationChoice = "no"; // Simulate user input
        if ("yes".equalsIgnoreCase(confirmationChoice)) {
            confirmBannerInsertion();
        } else {
            cancelBannerInsertion();
        }

        scanner.close();
        System.out.println("\n--- Banner Management UI Demo Finished ---");
    }
}
