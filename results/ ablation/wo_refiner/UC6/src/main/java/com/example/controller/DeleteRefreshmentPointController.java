package com.example.controller;

import com.example.actor.AgencyOperator;
import com.example.command.DeleteRefreshmentPointCommand;
import com.example.domain.RefreshmentPoint;
import com.example.error.ErrorHandler;
import java.util.List;
import java.util.Map;

/**
 * Controller for the Delete Refreshment Point use case.
 * Manages the interaction between the Agency Operator and the business logic.
 */
public class DeleteRefreshmentPointController {
    private AgencyOperator currentOperator;
    private SearchCulturalHeritageController searchController;
    private ErrorHandler errorHandler;
    private int selectedId;

    public DeleteRefreshmentPointController(AgencyOperator operator,
                                            SearchCulturalHeritageController searchController,
                                            ErrorHandler errorHandler) {
        this.currentOperator = operator;
        this.searchController = searchController;
        this.errorHandler = errorHandler;
    }

    /**
     * trace(R5/R6)
     * Flow step: Selects a point of rest and activates a function of elimination
     * @param selectedId The ID of the refreshment point to delete
     */
    public void activateDeleteFunction(int selectedId) {
        if (!currentOperator.isLoggedIn()) {
            displayErrorMessage("Operator not logged in.");
            return;
        }
        this.selectedId = selectedId;
        // In a real scenario, this would trigger UI to show the selected point
        System.out.println("Delete function activated for ID: " + selectedId);
    }

    public void handleDeleteRequest(int requestId) {
        this.selectedId = requestId;
        boolean confirm = displayConfirmation();
        if (confirm) {
            DeleteRefreshmentPointCommand command = new DeleteRefreshmentPointCommand(requestId);
            boolean success = command.execute();
            if (success) {
                displaySuccessMessage();
            } else {
                displayErrorMessage("Delete operation failed.");
            }
        } else {
            System.out.println("Operation cancelled by operator.");
            clearSelection();
        }
    }

    /**
     * trace(R7)
     * Requirement: Asks for confirmation of the transaction
     * In a real UI, this would display a dialog and wait for user input.
     * For simulation, we assume confirmation is granted.
     * @return true if confirmed, false if cancelled
     */
    public boolean displayConfirmation() {
        // Simulating confirmation dialog - always returns true for main success scenario.
        // In alternative flow, this could return false.
        System.out.println("Confirmation dialog shown for deleting refreshment point ID: " + selectedId);
        return true;
    }

    /**
     * trace(R10)
     * Exit condition: The system shall notify the successful elimination
     */
    public void displaySuccessMessage() {
        System.out.println("SUCCESS: Refreshment point with ID " + selectedId + " has been deleted.");
    }

    public void displayErrorMessage(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * trace(R12)
     * Handles connection errors as per sequence diagram.
     * @param error The exception that occurred
     */
    public void handleConnectionError(Exception error) {
        String userMessage = errorHandler.handleConnectionError(error);
        displayErrorMessage(userMessage);
    }

    // Helper method to retrieve list of refreshment points (integration with SearchCulturalHeritage)
    public List<RefreshmentPoint> retrieveRefreshmentPointList(Map<String, Object> criteria) {
        if (searchController == null) {
            throw new IllegalStateException("SearchCulturalHeritageController not set.");
        }
        return searchController.retrieveRefreshmentPointList(criteria);
    }

    public void notifySuccessfulElimination() {
        displaySuccessMessage();
    }

    public void clearSelection() {
        this.selectedId = -1;
        System.out.println("Selection cleared.");
    }
}