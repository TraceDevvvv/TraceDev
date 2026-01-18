package com.example.bannermanagement;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the user interface for the Agency Operator, displaying information
 * and collecting input. It interacts with the AgencyOperatorController.
 */
public class AgencyOperatorView {
    private final AgencyOperatorController controller;
    private final Scanner scanner; // For simulating user input

    /**
     * Constructs an AgencyOperatorView with a reference to its controller.
     *
     * @param controller The AgencyOperatorController instance.
     */
    public AgencyOperatorView(AgencyOperatorController controller) {
        this.controller = controller;
        this.controller.setView(this); // Set this view to the controller
        this.scanner = new Scanner(System.in); // Initialize scanner for input
    }

    /**
     * Displays a list of refreshment points to the operator.
     * @param points The list of RefreshmentPoint objects to display.
     */
    public void displayRefreshmentPoints(List<RefreshmentPoint> points) {
        System.out.println("\n--- Available Refreshment Points ---");
        if (points.isEmpty()) {
            System.out.println("No refreshment points found.");
        } else {
            points.forEach(System.out::println);
        }
        System.out.println("------------------------------------");
    }

    /**
     * Displays a list of banners to the operator.
     * @param banners The list of Banner objects to display.
     */
    public void displayBanners(List<Banner> banners) {
        System.out.println("\n--- Banners for Selected Refreshment Point ---");
        if (banners.isEmpty()) {
            System.out.println("No banners found for this refreshment point.");
        } else {
            banners.forEach(System.out::println);
        }
        System.out.println("----------------------------------------------");
    }

    /**
     * Displays a confirmation message to the operator.
     * @param message The confirmation message to display.
     */
    public void displayConfirmationMessage(String message) {
        System.out.println("\nCONFIRMATION: " + message + " (yes/no)");
    }

    /**
     * Displays a success message to the operator.
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("\nSUCCESS: " + message);
    }

    /**
     * Displays a message indicating that login is required. Added to satisfy requirement REQ-001.
     * @param message The message to display.
     */
    public void displayLoginRequiredMessage(String message) {
        System.out.println("\nLOGIN REQUIRED: " + message);
    }

    /**
     * Displays an error message to the operator. Added to satisfy requirement REQ-003.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\nERROR: " + message);
    }

    /**
     * Simulates getting user input for selecting a refreshment point ID.
     * In a real UI, this would come from a selection event.
     * @return The selected refreshment point ID as a String.
     */
    public String getSelectedRefreshmentPointIdInput() {
        System.out.print("Enter Refreshment Point ID to select: ");
        return scanner.nextLine();
    }

    /**
     * Simulates getting user input for selecting a banner ID.
     * In a real UI, this would come from a selection event.
     * @return The selected banner ID as a String.
     */
    public String getSelectedBannerIdInput() {
        System.out.print("Enter Banner ID to delete: ");
        return scanner.nextLine();
    }

    /**
     * Simulates getting user confirmation (yes/no).
     * @return true if user confirms, false otherwise.
     */
    public boolean getUserConfirmationInput() {
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    // --- Methods for Sequence Diagram Interaction ---

    /**
     * Simulates the 'attemptOperation()' action from the Agency Operator,
     * which initiates an authentication check. Added for SD interaction (R003).
     */
    public void attemptOperation() {
        System.out.println("\nAO -> View: attemptOperation()");
        // The View typically doesn't handle authentication logic directly
        // It asks the Controller to check authentication
        if (!controller.checkAuthentication()) {
            displayLoginRequiredMessage("Please log in first to proceed.");
            // In a real application, this would redirect to a login screen
            // For this simulation, we'll assume the Main class handles the login attempt
            return; // Stop if not authenticated
        }
        System.out.println("View: Authentication successful. Proceeding with operation.");
        // If authenticated, proceed to initDisplayRefreshmentPoints
        initDisplayRefreshmentPoints();
    }

    /**
     * Simulates the 'initDisplayRefreshmentPoints()' action from the Agency Operator.
     * Added for SD interaction (R004).
     */
    public void initDisplayRefreshmentPoints() {
        System.out.println("\nAO -> View: initDisplayRefreshmentPoints()");
        controller.showRefreshmentPoints();
    }

    /**
     * Simulates the 'selectRefreshmentPoint(rpId)' action from the Agency Operator.
     * @param rpId The refreshment point ID to select.
     */
    public void selectRefreshmentPoint(String rpId) {
        System.out.println("\nAO -> View: selectRefreshmentPoint(" + rpId + ")");
        controller.selectRefreshmentPoint(rpId);
    }

    /**
     * Simulates the 'accessBannerRemovalFunction()' action from the Agency Operator.
     */
    public void accessBannerRemovalFunction() {
        System.out.println("\nAO -> View: accessBannerRemovalFunction()");
        controller.accessBannerRemovalFunction();
    }

    /**
     * Simulates the 'selectBanner(bannerId)' action from the Agency Operator.
     * Added for SD interaction (R008).
     * @param bannerId The banner ID to select for deletion.
     */
    public void selectBanner(String bannerId) {
        System.out.println("\nAO -> View: selectBanner(" + bannerId + ")");
        controller.selectBannerToDelete(bannerId);
    }

    /**
     * Simulates the 'clickDeleteButton()' action from the Agency Operator.
     * Added for SD interaction (R009).
     */
    public void clickDeleteButton() {
        System.out.println("\nAO -> View: clickDeleteButton()");
        controller.confirmBannerDeletionRequest();
    }

    /**
     * Simulates the 'confirmOperation(confirmation)' action from the Agency Operator.
     * Added for SD interaction (R011).
     * @param confirmation True if the user confirms the operation, false otherwise.
     */
    public void confirmOperation(boolean confirmation) {
        System.out.println("\nAO -> View: confirmOperation(" + confirmation + ")");
        controller.performBannerDeletion(confirmation);
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        scanner.close();
    }
}