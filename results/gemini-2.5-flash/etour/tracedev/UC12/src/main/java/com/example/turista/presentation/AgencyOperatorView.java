package com.example.turista.presentation;

import com.example.turista.domain.TuristaDTO;
import java.util.Scanner; // For simulating user input

/**
 * Represents the view component for the Agency Operator.
 * This class is responsible for displaying Turista information and error messages
 * and for capturing user input (simulated).
 * note left of AgencyOperatorView: Assumes a list of Turista IDs is available for selection (R4)
 * note right of AgencyOperatorView: Directly interacts with Agency Operator (R2)
 */
public class AgencyOperatorView {
    // - selectedTuristaId : String
    private String selectedTuristaId; // Stores the currently selected ID internally.
    private AgencyOperatorController controller; // Dependency on Controller

    /**
     * Sets the controller for this view.
     * This is typically done during application startup (e.g., dependency injection).
     * @param controller The controller to which this view will send user actions.
     */
    public void setController(AgencyOperatorController controller) {
        this.controller = controller;
    }

    /**
     * Displays the details of a Turista in a formatted card view.
     * @param turistaDTO The Data Transfer Object containing the Turista's details.
     */
    public void displayTuristaCard(TuristaDTO turistaDTO) {
        System.out.println("\n--- Turista Card Display ---");
        System.out.println(turistaDTO.toString());
        System.out.println("----------------------------\n");
    }

    /**
     * Simulates getting a selected Turista ID from the UI.
     * In a real UI, this would come from a user selecting an item from a list.
     * @return A simulated Turista ID.
     */
    public String getSelectedTuristaIdFromUI() {
        // Simulating user input for testing purposes
        System.out.print("\nEnter Turista ID to view details (e.g., 101, 102, 103 for success/not found/error): ");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        // For actual UI, `scanner` should be managed properly, e.g., closed or passed in.
        // For this simple demo, we'll just get the input.
        return id;
    }

    /**
     * Notifies the controller that a Turista has been selected by the operator.
     * Precondition: a Turista is selected (R5)
     * Triggered by Agency Operator selecting a Turista (R6)
     *
     * @param turistaId The ID of the selected Turista.
     * note right of AgencyOperatorView::notifySelection: Precondition: a Turista is selected (R5)
     * note right of AgencyOperatorView::notifySelection: Triggered by Agency Operator selecting a Turista (R6)
     */
    public void notifySelection(String turistaId) {
        this.selectedTuristaId = turistaId; // Store internally, though not strictly used in this sequence.
        System.out.println("View: Operator selected Turista ID: " + turistaId);
        // Precondition: List of Turista available from RicercaTurista UC (R4)
        // Precondition: Turista is selected (R5)
        // View -> Controller: handleDisplayCardRequest(turistaId : String) (R7)
        if (controller != null) {
            controller.handleDisplayCardRequest(turistaId);
        } else {
            System.err.println("View: Error: Controller not set. Cannot handle selection.");
        }
    }

    /**
     * Displays an error message to the Agency Operator.
     * @param message The error message to display.
     * note right of AgencyOperatorView::showErrorMessage: Displays error messages, e.g., for ETOUR server connection interruption (R10)
     */
    public void showErrorMessage(String message) {
        System.err.println("\n!!! ERROR: " + message + " !!!\n");
    }
}