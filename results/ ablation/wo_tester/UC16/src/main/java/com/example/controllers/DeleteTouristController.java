package com.example.controllers;

import com.example.domain.AgencyOperator;
import com.example.domain.Tourist;
import com.example.repository.TouristRepository;

/**
 * Controller responsible for the tourist deletion flow.
 */
public class DeleteTouristController {
    private TouristRepository touristRepository;
    private AgencyOperator operator;

    public DeleteTouristController(TouristRepository repo, AgencyOperator op) {
        this.touristRepository = repo;
        this.operator = op;
    }

    /**
     * Executes the complete deletion flow for a tourist.
     * Implements the sequence diagram steps.
     * @param touristId ID of tourist to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean executeDeletionFlow(String touristId) {
        // Validate operator (entry condition)
        if (!validateOperator()) {
            System.out.println("Error: Operator not logged in");
            return false;
        }

        // Fetch tourist details
        Tourist tourist = touristRepository.findById(touristId);
        if (tourist == null) {
            System.out.println("Tourist not found");
            return false;
        }

        // Display tourist details (in real UI, this would be shown to operator)
        System.out.println("Tourist details for deletion: " + tourist);

        // Request confirmation (simulate UI dialog)
        boolean confirmed = requestConfirmation();
        if (!confirmed) {
            System.out.println("Operation cancelled by operator");
            return false;
        }

        // Perform permanent deletion
        try {
            performDeletion(touristId);
        } catch (RuntimeException e) {
            // This handles the optional "Connection to server ETOUR interrupted" scenario.
            // In the sequence diagram, the database sends ConnectionLost and the controller calls rollbackTransaction.
            System.out.println("Error: Deletion failed due to connection loss");
            touristRepository.rollbackTransaction();
            return false;
        }

        System.out.println("Operation successful: Tourist account permanently deleted");
        return true;
    }

    /**
     * Requests confirmation from the operator.
     * In the sequence diagram, this shows a confirmation dialog and receives a boolean response.
     * @return true if operator confirms deletion
     */
    public boolean requestConfirmation() {
        // In a real system, this would be a UI interaction.
        // Here we simulate by calling the operator's confirmDeletion method.
        return operator.confirmDeletion();
    }

    /**
     * Performs the actual deletion by calling the repository.
     * @param touristId ID of tourist to delete
     */
    public void performDeletion(String touristId) {
        touristRepository.permanentDelete(touristId);
    }

    /**
     * Validates that the operator is logged in (entry condition).
     * @return true if operator is logged in
     */
    public boolean validateOperator() {
        return operator != null && operator.isLoggedIn();
    }

    /**
     * This method corresponds to sequence message "Show confirmation dialog" (m16).
     * It shows a dialog to the operator and returns the confirmation result.
     */
    public boolean showConfirmationDialog() {
        return requestConfirmation();
    }

    /**
     * This method corresponds to sequence message "Display tourist details" (m14).
     * It displays the tourist details to the operator (console for simulation).
     */
    public void displayTouristDetails(Tourist tourist) {
        System.out.println("Display tourist details: " + tourist);
    }

    /**
     * This method corresponds to sequence message "Display tourists list" (m6).
     * It displays a list of tourists to the operator.
     */
    public void displayTouristsList(java.util.List<Tourist> tourists) {
        System.out.println("Display tourists list: " + tourists);
    }

    /**
     * This method corresponds to sequence message "Operation successful" (m23).
     * It informs the operator that the deletion was successful.
     */
    public void operationSuccessful() {
        System.out.println("Operation successful");
    }

    /**
     * This method corresponds to sequence message "Operation cancelled" (m25).
     * It informs the operator that the operation was cancelled.
     */
    public void operationCancelled() {
        System.out.println("Operation cancelled");
    }

    /**
     * This method corresponds to sequence message "Return to main view" (m27).
     * It returns the operator to the main view.
     */
    public void returnToMainView() {
        System.out.println("Return to main view");
    }
}