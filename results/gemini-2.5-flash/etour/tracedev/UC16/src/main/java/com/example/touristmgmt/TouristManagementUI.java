package com.example.touristmgmt;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the User Interface for Tourist Management operations.
 * This class simulates UI interactions by printing to console and
 * taking simple input for confirmation.
 */
public class TouristManagementUI {
    private TouristDeletionController touristDeletionController;
    private TouristSearchService touristSearchService; // Added for R4

    // Scanner for simulated user input in console
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for TouristManagementUI.
     *
     * @param touristDeletionController The controller responsible for deletion logic.
     * @param touristSearchService The service for searching tourists.
     */
    public TouristManagementUI(TouristDeletionController touristDeletionController, TouristSearchService touristSearchService) {
        this.touristDeletionController = touristDeletionController;
        this.touristSearchService = touristSearchService;
    }

    /**
     * Method to set the controller after initial construction.
     * This is used to resolve circular dependencies during object instantiation in Main.
     * It is not explicitly in the UML but is a necessary pattern for a runnable example.
     *
     * @param touristDeletionController The TouristDeletionController instance.
     */
    public void setTouristDeletionController(TouristDeletionController touristDeletionController) {
        this.touristDeletionController = touristDeletionController;
    }


    /**
     * Simulates the UI handling a selection of a tourist for deletion.
     * This method will call the controller to initiate the deletion flow.
     * Renamed from requestDeletionInitiation to handleTouristSelectionForDeletion to satisfy R5.
     *
     * @param touristId The ID of the tourist selected for deletion.
     */
    public void handleTouristSelectionForDeletion(String touristId) {
        System.out.println("\nUI: Agency Operator selected tourist with ID: " + touristId + " for deletion.");
        // Step 2: Agency Operator selects a tourist from the list.
        if (touristDeletionController == null) {
            System.err.println("UI Error: TouristDeletionController not set. Cannot initiate deletion flow.");
            return;
        }
        touristDeletionController.initiateDeletionFlow(touristId);
    }

    /**
     * Displays a confirmation prompt to the user and waits for input.
     *
     * @param message The confirmation message to display.
     * @return true if the user confirms, false otherwise.
     */
    public boolean showConfirmationPrompt(String message) {
        System.out.println("\nUI: " + message + " (y/n)");
        // Step 4: System asks for confirmation of the transaction.
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }

    /**
     * Displays the result of an operation to the user.
     *
     * @param message The message to display.
     */
    public void displayOperationResult(String message) {
        System.out.println("\nUI: " + message);
        // UI displays message to Agency Operator.
    }

    /**
     * Simulates displaying the list of tourists.
     */
    public void displayAllTourists() {
        System.out.println("\nUI: Displaying all tourists:");
        List<Tourist> tourists = touristSearchService.getAllTourists();
        if (tourists.isEmpty()) {
            System.out.println("UI: No tourists found.");
            return;
        }
        for (Tourist tourist : tourists) {
            System.out.println("  - " + tourist.getTouristId() + ": " + tourist.getName() + " (" + tourist.getEmail() + ")");
        }
        System.out.println("UI: --- End of list ---");
    }

    // In a real UI, you might have methods like:
    // public void showErrorMessage(String error) { ... }
    // public void updateTouristList(List<Tourist> tourists) { ... }
}