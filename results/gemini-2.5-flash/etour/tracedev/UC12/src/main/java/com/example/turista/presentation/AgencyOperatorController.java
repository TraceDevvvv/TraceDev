package com.example.turista.presentation;

import com.example.turista.application.TuristaNotFoundException;
import com.example.turista.application.TuristaService;
import com.example.turista.domain.TuristaDTO;

/**
 * Controller for handling requests from the Agency Operator related to Turista.
 * This class acts as an intermediary between the view and the application service layer.
 * note left of AgencyOperatorController: Requires Agency Operator to be logged in (R3)
 * note right of AgencyOperatorController: Orchestrates interactions from Agency Operator (R2)
 */
public class AgencyOperatorController {
    // - turistaService : TuristaService
    private TuristaService turistaService;
    // - view : AgencyOperatorView
    private AgencyOperatorView view;

    /**
     * Constructor for AgencyOperatorController.
     * @param turistaService The service layer for Turista operations.
     * @param view The associated view for displaying information and errors.
     */
    public AgencyOperatorController(TuristaService turistaService, AgencyOperatorView view) {
        this.turistaService = turistaService;
        this.view = view;
        // Relationship: AgencyOperatorController "1" --> "1" AgencyOperatorView : updates > (R9)
        // Relationship: AgencyOperatorController "1" --> "1" TuristaService : requests data >
    }

    /**
     * Handles the request to display a Turista's card details.
     * This method retrieves data via the TuristaService and then updates the view.
     *
     * @param turistaId The ID of the turista whose card details are requested.
     * note right of AgencyOperatorController::handleDisplayCardRequest: Handles request to display card (R7)
     */
    public void handleDisplayCardRequest(String turistaId) {
        System.out.println("  Controller: Handling display card request for Turista ID: " + turistaId);

        if (turistaId == null || turistaId.trim().isEmpty()) {
            view.showErrorMessage("Turista ID cannot be empty.");
            return;
        }

        try {
            // Controller -> Service: getTuristaCardDetails(turistaId : String)
            TuristaDTO turistaDTO = turistaService.getTuristaCardDetails(turistaId);
            // Service --> Controller: turistaDTO : TuristaDTO
            // note over Controller: The successful flow ensures accurate and timely retrieval and display of information (R11)
            // Controller -> View: displayTuristaCard(turistaDTO : TuristaDTO)
            view.displayTuristaCard(turistaDTO);
            System.out.println("  Controller: Successfully displayed Turista card for ID: " + turistaId);
        } catch (TuristaNotFoundException e) {
            // Service --> Controller: Exception: TuristaNotFoundException
            // Controller -> View: showErrorMessage(...)
            System.err.println("  Controller: Error retrieving Turista details for ID " + turistaId + ": " + e.getMessage());
            // note right of AgencyOperatorView::showErrorMessage: Displays error messages, e.g., for ETOUR server connection interruption (R10)
            view.showErrorMessage("Cannot retrieve Turista details. " + e.getMessage());
        } catch (Exception e) {
            // Catch any unexpected exceptions
            System.err.println("  Controller: An unexpected error occurred: " + e.getMessage());
            view.showErrorMessage("An unexpected error occurred while retrieving Turista details.");
        }
    }
}