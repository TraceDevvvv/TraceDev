package com.example.agencyapp.presentation;

import com.example.agencyapp.application.RefreshmentPointDeletionService;
import com.example.agencyapp.domain.RefreshmentPoint;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The user interface for the Agency Operator.
 * This class handles displaying information and receiving input from the operator,
 * and delegates business logic to the application layer.
 */
public class AgencyOperatorUI {
    // Relationship: AgencyOperatorUI uses RefreshmentPointDeletionService
    private final RefreshmentPointDeletionService refreshmentPointDeletionService;
    private String selectedRefreshmentPointId; // Stores the ID of the currently selected point.

    // A scanner for simulating user input. In a real UI, this would be GUI events.
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an AgencyOperatorUI with a dependency on the RefreshmentPointDeletionService.
     * @param service The application service to handle refreshment point deletion.
     */
    public AgencyOperatorUI(RefreshmentPointDeletionService service) {
        this.refreshmentPointDeletionService = service;
    }

    /**
     * Displays a list of refreshment points to the operator.
     * (Assumed to be coming from "RicercaBeneCulturale" as per sequence diagram note).
     * @param points A list of RefreshmentPoint objects to display.
     */
    public void displayRefreshmentPoints(List<RefreshmentPoint> points) {
        System.out.println("\nUI: Displaying available Refreshment Points:");
        if (points == null || points.isEmpty()) {
            System.out.println("UI: No refreshment points to display.");
        } else {
            points.forEach(point -> System.out.println("UI:   - ID: " + point.getId() + ", Name: " + point.getName() + ", Location: " + point.getLocation()));
        }
    }

    /**
     * Simulates the operator selecting a refreshment point by its ID.
     * This method stores the selected ID for subsequent operations. (REQ-005)
     * @param id The ID of the refreshment point selected by the operator.
     */
    public void selectRefreshmentPoint(String id) {
        this.selectedRefreshmentPointId = id;
        System.out.println("\nUI: Refreshment Point ID '" + id + "' selected by operator.");
    }

    /**
     * Activates the deletion function for the currently selected refreshment point.
     * This method orchestrates the full deletion process as described in the sequence diagram.
     */
    public void activateDeletionFunction() {
        System.out.println("\nUI: Operator activates deletion function.");
        if (selectedRefreshmentPointId == null || selectedRefreshmentPointId.isEmpty()) {
            showErrorMessage("No refreshment point selected for deletion. Please select one first.");
            return;
        }

        // REQ-007, REQ-008: UI processes confirmation outcome internally
        boolean confirmed = showConfirmationDialog(selectedRefreshmentPointId);

        if (confirmed) {
            System.out.println("UI: User confirmed deletion for ID: " + selectedRefreshmentPointId);
            // REQ-009: Parameter name consistency
            boolean success = refreshmentPointDeletionService.deleteRefreshmentPoint(selectedRefreshmentPointId);

            if (success) {
                showSuccessMessage(); // Exit Condition: Successful elimination.
            } else {
                showErrorMessage("Failed to delete refreshment point. Please try again."); // Exit Condition: ETOUR connection interrupted or other system error. (REQ-012 verified as TRACED)
            }
        } else {
            showCancellationMessage("Operation cancelled by Agency Operator."); // Exit Condition: Operation cancelled.
        }
    }

    /**
     * Displays a confirmation dialog to the operator and captures their response.
     * In a real UI, this would be a modal dialog. Here, it simulates with console input.
     * (REQ-007, REQ-008: Parameter name consistency; now models user interaction outcome and return)
     * @param id The ID of the refreshment point to be deleted, for display in the dialog.
     * @return true if the operator confirms, false otherwise.
     */
    public boolean showConfirmationDialog(String id) {
        System.out.print("UI: Are you sure you want to delete Refreshment Point ID '" + id + "'? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return "yes".equals(input);
    }

    /**
     * Displays a success message to the operator.
     */
    public void showSuccessMessage() {
        System.out.println("\nUI: [SUCCESS] Refreshment point deleted successfully.");
    }

    /**
     * Displays an error message to the operator.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\nUI: [ERROR] " + message);
    }

    /**
     * Displays a cancellation message to the operator.
     * @param message The cancellation message to display.
     */
    public void showCancellationMessage(String message) {
        System.out.println("\nUI: [CANCELLED] " + message);
    }
}