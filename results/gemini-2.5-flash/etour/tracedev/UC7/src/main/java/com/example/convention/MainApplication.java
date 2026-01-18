package com.example.convention;

import com.example.convention.controller.AgencyOperatorController;
import com.example.convention.repository.JpaConventionRepository;
import com.example.convention.repository.JpaRefreshmentPointRepository;
import com.example.convention.service.ConventionActivationService;
import com.example.convention.service.ETOURService;

/**
 * Main application class to demonstrate the Convention Activation Flow.
 * This class sets up the dependencies and orchestrates the sequence diagram.
 */
public class MainApplication {
    public static void main(String[] args) {
        // --- Dependency Injection Setup (Manual for demonstration) ---
        JpaConventionRepository conventionRepository = new JpaConventionRepository();
        JpaRefreshmentPointRepository refreshmentPointRepository = new JpaRefreshmentPointRepository();
        ETOURService etourService = new ETOURService();

        ConventionActivationService conventionActivationService =
            new ConventionActivationService(conventionRepository, etourService, refreshmentPointRepository);

        AgencyOperatorController controller = new AgencyOperatorController(conventionActivationService);

        // --- Simulate the Sequence Diagram Flow ---

        // Scenario 1: Successful Activation with ETOUR Notification (for CONV001, which uses designated RP001)
        System.out.println("--- Scenario 1: Successful Activation (Designated RP) ---");
        controller.enableActivation(); // Calls the renamed method to match sequence diagram

        System.out.println("\n--- Scenario 2: Activation Failure (Non-Designated RP) ---");
        // To demonstrate activation failure, we manually call confirmActivation for CONV004 (uses non-designated RP004)
        System.out.println("UI: Simulating operator trying to activate CONV004 (Non-Designated RP)");
        // First get details for CONV004 to show it to the operator
        System.out.println("UI->Service: getConventionDetails(CONV004)");
        controller.handleActivationRequest("CONV004"); // Just to show the details flow
        System.out.println("UI: Operator reviews details and decides to activate CONV004.");
        controller.confirmActivation("CONV004"); // This will fail due to RP004 not being designated

        System.out.println("\n--- Scenario 3: Attempt to activate an already ACTIVE convention ---");
        System.out.println("UI: Simulating operator trying to activate CONV003 (already ACTIVE)");
        // First get details for CONV003
        System.out.println("UI->Service: getConventionDetails(CONV003)");
        controller.handleActivationRequest("CONV003"); // Just to show the details flow
        System.out.println("UI: Operator reviews details and decides to activate CONV003.");
        controller.confirmActivation("CONV003"); // This should report success as it's already active
    }
}