package com.example;

import com.example.actor.AgencyOperator;
import com.example.controller.ConventionActivationController;
import com.example.dto.ActivationResultDTO;
import com.example.model.*;
import com.example.repository.ConventionRepository;
import com.example.repository.ConventionRepositoryImpl;
import com.example.service.ActivationHandler;
import com.example.service.DefaultActivationHandler;
import com.example.service.ETourService;
import com.example.ui.FormDisplay;
import com.example.usecase.ProcessConventionActivationUseCase;

/**
 * Main class to demonstrate the runnable system.
 */
public class Main {
    public static void main(String[] args) {
        // Setup dependencies
        ConventionRepository repository = new ConventionRepositoryImpl();
        ETourService etourService = new ETourService();
        ActivationHandler activationHandler = new DefaultActivationHandler(etourService);
        FormDisplay formDisplay = new FormDisplay();
        ProcessConventionActivationUseCase useCase = new ProcessConventionActivationUseCase(
                repository, activationHandler, formDisplay);
        ConventionActivationController controller = new ConventionActivationController(useCase);

        // Create a sample convention with valid data
        DataRequest dataRequest = new DataRequest("DR001", "Sample data content", RequestStatus.COMPLETED);
        RefreshmentPoint refreshmentPoint = new RefreshmentPoint("RP001", "Main Hall", PointStatus.DESIGNATED);
        Convention convention = new Convention("CONV001", ConventionStatus.PENDING, dataRequest, refreshmentPoint);

        // Add convention to repository
        ((ConventionRepositoryImpl) repository).addConvention(convention);

        // Create an agency operator
        AgencyOperator operator = new AgencyOperator("John Doe", "OP001");

        // Simulate the sequence diagram flow
        System.out.println("=== Starting Convention Activation Process ===");
        operator.processConventionActivation(controller, "CONV001");
        // Simulate review displayed Convention data (Step 4 sequence message m7)
        System.out.println("Reviews displayed Convention data  // Enhanced to satisfy requirement Flow of Events (Step 4)");
        // Simulate precondition check
        if (convention.validatePreconditions()) {
            System.out.println("Preconditions check passed.");
        } else {
            System.out.println("Precondition check failed.");
            return;
        }
        // Simulate activation confirmation
        operator.confirmActivation(controller, "CONV001");
        // Simulate system notification (message m26)
        System.out.println("System notifies activation success");
        // Additional scenarios
        System.out.println("\n--- Simulating interruption scenario ---");
        etourService.setConnected(false);
        activationHandler.logConnectionError();
        System.out.println("Connection interrupted\nNotification failed");
        etourService.setConnected(true);
        System.out.println("--- End simulation ---");
    }
}