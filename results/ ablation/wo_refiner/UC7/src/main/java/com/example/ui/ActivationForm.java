
package com.example.ui;

import com.example.controller.ConventionActivationController;
import com.example.dto.ActivationRequestDTO;
import com.example.dto.ActivationResultDTO;
import com.example.model.Agency;

/**
 * User interface form for convention activation.
 * Interacts with Agency Operator and Controller.
 */
public class ActivationForm {
    private ConventionActivationController controller;
    
    public ActivationForm(ConventionActivationController controller) {
        this.controller = controller;
    }
    
    /**
     * Enables the activation function (REQ-006).
     * Called when the user opens the activation interface.
     */
    public void enableActivationFunction() {
        System.out.println("Activation function enabled");
        System.out.println("Activation form interface is now ready.");
        
        // Check pre-condition: Agency must have designated refreshment point
        Agency agency = new Agency("AG001", "Main Agency");
        if (agency.getDesignatedRefreshmentPoint() != null && 
            agency.getDesignatedRefreshmentPoint().isDesignated()) {
            System.out.println("Pre-condition satisfied: Agency has designated refreshment point");
        } else {
            System.out.println("Warning: Agency does not have a designated refreshment point");
        }
    }
    
    /**
     * Loads and displays the activation form with convention data.
     * 
     * @param requestId The request identifier
     * @return ActivationRequestDTO with loaded data
     */
    public ActivationRequestDTO loadAndDisplayForm(String requestId) {
        System.out.println("Loading form for request: " + requestId);
        
        // Create activation request
        ActivationRequestDTO request = new ActivationRequestDTO(requestId, "OP001");
        
        // First call to controller to load data
        ActivationResultDTO initialResult = controller.activateConvention(request);
        
        if (initialResult.isSuccess()) {
            System.out.println("Form displayed with convention data for request: " + requestId);
            return request;
        } else {
            displayError(initialResult.getMessage());
            return null;
        }
    }
    
    /**
     * Shows a confirmation dialog to the user (REQ-009).
     * 
     * @return true if user confirms, false otherwise
     */
    public boolean showConfirmationDialog() {
        System.out.println("Showing confirmation dialog");
        System.out.println("Please confirm the activation of the convention.");
        System.out.println("1. Confirm activation");
        System.out.println("2. Cancel");
        
        // In a real UI, this would show a dialog and wait for user input
        // For simulation, we'll assume user confirms
        System.out.println("User selected: Confirm activation");
        return true;
    }
    
    /**
     * Displays the activation result.
     * 
     * @param result The activation result to display
     */
    public void displayResult(ActivationResultDTO result) {
        if (result.isSuccess()) {
            System.out.println("Activation successful for convention: " + result.getConventionId());
            System.out.println("Message: " + result.getMessage());
        } else {
            System.out.println("Activation failed for convention: " + result.getConventionId());
            System.out.println("Error: " + result.getMessage());
        }
    }
    
    /**
     * Displays an error message.
     * 
     * @param message The error message to display
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}
