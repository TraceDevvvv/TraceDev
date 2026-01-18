package com.example.justification.controller;

import com.example.justification.service.JustificationService;

import java.util.Objects;

/**
 * Controller for Administrator actions related to Justifications.
 * This class handles incoming requests from the UI (simulated) and orchestrates
 * the application service layer.
 * Corresponds to the AdministratorController class in the Class Diagram and the UI participant in the sequence diagram.
 */
public class AdministratorController {
    private final JustificationService _justificationService;

    /**
     * Constructs an AdministratorController with its required service dependency.
     *
     * @param justificationService The JustificationService instance to use for business operations.
     */
    public AdministratorController(JustificationService justificationService) {
        this._justificationService = Objects.requireNonNull(justificationService, "JustificationService cannot be null");
    }

    /**
     * Handles the request to delete a justification.
     * This method simulates the UI interaction flow described in the sequence diagram.
     *
     * @param justificationId The ID of the justification to be deleted.
     * @return An ActionResult indicating the outcome of the operation.
     */
    public ActionResult handleDeleteJustification(String justificationId) {
        System.out.println("\nAdministrator (UI): Clicks \"Delete\" for Justification ID: " + justificationId);
        System.out.println("UI: Displays confirmation dialog...");

        // Simulate confirmation from Administrator
        boolean confirmed = true; // For demonstration, assume Administrator Confirms Deletion
        if (!confirmed) {
            System.out.println("UI --> Administrator: Operation cancelled.");
            // Exit Condition: Administrator interrupts.
            return ActionResult.failure("Operation cancelled by administrator.");
        }

        System.out.println("UI: Administrator Confirms Deletion.");
        try {
            // Delegate the elimination logic to the JustificationService.
            _justificationService.eliminateJustification(justificationId);

            System.out.println("UI: JustificationService returned success.");
            System.out.println("UI: Redirects to registry screen.");
            // Exit Condition: System returns to registry screen.
            System.out.println("UI --> Administrator: \"Justification successfully removed.\"");
            // Exit Condition: Justification eliminated.
            return ActionResult.success("Justification successfully removed.");
        } catch (RuntimeException e) {
            // Alt: Justification Not Found / System Error
            System.err.println("UI: JustificationService returned error: " + e.getMessage());
            // Modified to satisfy requirement: Exit Condition (Connection to the SMOS server interrupted)
            String errorMessage = "Error: Operation failed. " +
                                  (e.getMessage().contains("Connection to SMOS server interrupted") ? "Connection to SMOS server interrupted." : "Justification not found or an unexpected error occurred.");
            System.err.println("UI --> Administrator: \"" + errorMessage + "\"");
            // Exit Condition: Connection interrupted or other error.
            return ActionResult.failure(errorMessage);
        }
    }
}