package com.example.convention.controller;

import com.example.convention.dto.ConventionDTO;
import com.example.convention.service.ConventionActivationService;

/**
 * Controller class for Agency Operator interactions related to conventions.
 * Acts as the entry point for UI requests.
 */
public class AgencyOperatorController {
    private ConventionActivationService conventionActivationService;

    /**
     * Constructor for AgencyOperatorController.
     *
     * @param conventionActivationService The service to handle convention activation logic.
     */
    public AgencyOperatorController(ConventionActivationService conventionActivationService) {
        this.conventionActivationService = conventionActivationService;
    }

    /**
     * Initiates the activation flow for the Agency Operator.
     * [R4] Method `enableActivation` implemented to satisfy requirement R4.
     * This method simulates the initial step where an operator starts the process.
     * It will typically lead to `handleActivationRequest` or similar.
     */
    public void enableActivation() { // Renamed from initiateActivationFlow to match sequence diagram message 'enableActivation()'
        System.out.println("\n--- Agency Operator Initiates Activation Flow ---");
        System.out.println("UI: 1. Agency Operator enables activation function. [R4]");
        // Simulate a fixed convention ID for demonstration
        String sampleConventionId = "CONV001";
        System.out.println("UI: Requesting details for convention: " + sampleConventionId);
        // This implicitly calls getConventionDetails for display
        ConventionDTO dto = handleActivationRequest(sampleConventionId); // Calls service for details
        if (dto != null) {
            displayConventionForm(dto); // Displays details to operator
            System.out.println("UI: 3. Operator checks data and decides for activation. [R6]");
            requestConfirmation(); // Asks for confirmation
            // In a real app, this would be a user input, then confirmActivation is called.
            // For sequence diagram flow, we directly call confirmActivation
            // Assume operator confirms for the sample ID
            confirmActivation(sampleConventionId);
        } else {
            System.out.println("UI: Failed to load convention details for " + sampleConventionId);
        }
        System.out.println("----------------------------------------------\n");
    }

    /**
     * Handles the request to retrieve and display convention details.
     * [R5] This method is called internally after enableActivation to fulfill R5.
     *
     * @param conventionId The ID of the convention.
     * @return ConventionDTO with details, or null if not found.
     */
    public ConventionDTO handleActivationRequest(String conventionId) {
        System.out.println("UI->Service: getConventionDetails(" + conventionId + ") [R5]");
        // UI->Service: getConventionDetails(conventionId)
        return conventionActivationService.getConventionDetails(conventionId);
    }

    /**
     * Simulates displaying the convention data on a form.
     * [R5] Display part to satisfy requirement R5.
     *
     * @param conventionDTO The DTO containing convention details.
     */
    private void displayConventionForm(ConventionDTO conventionDTO) {
        System.out.println("UI: 2. System loads and displays convention data. [R5]");
        if (conventionDTO != null) {
            System.out.println("UI: Displaying Convention Details:");
            System.out.println("  ID: " + conventionDTO.id);
            System.out.println("  Refreshment Point: " + conventionDTO.refreshmentPointName);
            System.out.println("  Status: " + conventionDTO.status);
            System.out.println("  Details: " + conventionDTO.details);
        } else {
            System.out.println("UI: No convention data to display.");
        }
    }

    /**
     * Simulates the UI asking for confirmation from the operator.
     * [R7] Part of the interaction flow for R7.
     */
    private void requestConfirmation() {
        System.out.println("UI: 4. System asks for confirmation. [R7]");
        System.out.println("UI: Do you want to activate this convention? (Simulated 'yes')");
    }

    /**
     * Confirms the activation of a convention after operator decision.
     * [R8] Method `confirmActivation` implemented to satisfy requirement R8.
     *
     * @param conventionId The ID of the convention to confirm activation for.
     * @return A status message regarding the activation.
     */
    public String confirmActivation(String conventionId) {
        System.out.println("UI: 5. Agency Operator confirms. [R8]");
        System.out.println("UI->Service: activateConvention(" + conventionId + ")");

        // UI->Service: activateConvention(conventionId)
        boolean activationSuccess = conventionActivationService.activateConvention(conventionId);

        if (activationSuccess) {
            // Retrieve the updated convention details to check for ETOUR notification status
            ConventionDTO updatedConvention = conventionActivationService.getConventionDetails(conventionId);
            if (updatedConvention != null && "ACTIVE".equals(updatedConvention.status)) {
                // Check if the details contain the ETOUR failure tag added by the service
                if (updatedConvention.details != null && updatedConvention.details.contains("[ETOUR notification failed]")) {
                     notifyActivationSuccess("Convention activated, but ETOUR notification failed. Please check ETOUR logs.");
                     return "Activation successful with ETOUR warning.";
                } else {
                     notifyActivationSuccess("Convention activated and ETOUR notified.");
                     return "Activation successful.";
                }
            } else {
                // This path should ideally not be reached if activationSuccess is true and status becomes ACTIVE.
                // If it's already active (as in Scenario 3), service returns true, and status is ACTIVE.
                // If ETOUR failed, convention status is still ACTIVE.
                // The message below handles cases where status might not be ACTIVE for some other reason,
                // or if the updatedConvention DTO couldn't be retrieved.
                notifyActivationSuccess("Convention activated (status may need verification).");
                return "Activation successful (status may need verification).";
            }
        } else {
            notifyActivationFailure("Activation failed: Associated refreshment point is not designated.");
            return "Activation failed.";
        }
    }

    /**
     * Simulates notifying the UI about successful activation.
     * [R10] Notification part to satisfy requirement R10.
     *
     * @param message The success message.
     */
    private void notifyActivationSuccess(String message) {
        System.out.println("UI: System notifies activation of convention.");
        System.out.println("UI: " + message);
    }

    /**
     * Simulates notifying the UI about failed activation.
     *
     * @param message The failure message.
     */
    private void notifyActivationFailure(String message) {
        System.out.println("UI: System notifies activation failure.");
        System.out.println("UI: " + message);
    }
}