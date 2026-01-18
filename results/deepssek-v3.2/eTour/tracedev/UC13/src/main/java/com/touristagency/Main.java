package com.touristagency;

import com.touristagency.application.interactor.ToggleTouristStatusInteractor;
import com.touristagency.domain.TouristRepository;
import com.touristagency.frameworks.serv.NotificationService;
import com.touristagency.frameworks.serv.ServerConnectionService;
import com.touristagency.frameworks.web.WebController;
import com.touristagency.infrastructure.persistence.TouristRepositoryImpl;
import com.touristagency.interfaceadapters.controllers.ToggleTouristStatusController;
import com.touristagency.interfaceadapters.dtos.ToggleStatusResponseDTO;

/**
 * Main class to simulate the sequence diagram flow.
 * Acts as the entry point and simulates the Agency Operator and Web UI.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tourist Account Activation/Deactivation Sequence Simulation ===\n");

        // Setup the dependency chain (as per Clean Architecture)
        TouristRepository repository = new TouristRepositoryImpl();
        ToggleTouristStatusInteractor interactor = new ToggleTouristStatusInteractor(repository);
        ToggleTouristStatusController adapterController = new ToggleTouristStatusController(interactor);
        NotificationService notificationService = new NotificationService();
        ServerConnectionService connectionService = new ServerConnectionService();
        WebController webController = new WebController(adapterController);

        // Simulate Agency Operator actions (as per sequence diagram)
        // Precondition: Logged in (assumed)
        System.out.println("Agency Operator: Logged in.");
        System.out.println("Agency Operator selects 'Activate/Deactivate tourist' feature.");
        System.out.println("Web UI displays confirmation dialog.");
        System.out.println("Agency Operator confirms operation.\n");

        // Simulate activation of tourist123
        String touristId = "tourist123";
        boolean targetStatus = true; // activate
        System.out.println("Web UI calls WebController.toggleStatus(" + touristId + ", " + targetStatus + ")");

        // Execute the flow
        ToggleStatusResponseDTO response = webController.toggleStatus(touristId, targetStatus);

        // Show final outcome to Agency Operator
        System.out.println("\nWeb UI displays notification outcome:");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Message: " + response.getMessage());

        // Also test deactivation
        System.out.println("\n--- Second operation: Deactivate tourist456 ---");
        touristId = "tourist456";
        targetStatus = false; // deactivate
        response = webController.toggleStatus(touristId, targetStatus);
        System.out.println("Result: " + response.getMessage());

        // Test non-existent tourist
        System.out.println("\n--- Third operation: Non-existent tourist ---");
        touristId = "unknown";
        targetStatus = true;
        response = webController.toggleStatus(touristId, targetStatus);
        System.out.println("Result: " + response.getMessage());
    }
}