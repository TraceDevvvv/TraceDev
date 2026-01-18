package com.example.culturalgoods;

import com.example.culturalgoods.controller.CulturalGoodController;
import com.example.culturalgoods.dto.CulturalGoodDTO;
import com.example.culturalgoods.repository.CulturalGoodRepositoryImpl;
import com.example.culturalgoods.service.AuthenticationService;
import com.example.culturalgoods.service.CulturalGoodApplicationService;
import com.example.culturalgoods.ui.UI;

import java.time.LocalDate;

/**
 * Main class to run the Cultural Good Insertion Use Case.
 * This simulates the interactions from the sequence diagram.
 */
public class Main {

    public static void main(String[] args) {
        // 1. Initialize components
        AuthenticationService authenticationService = new AuthenticationService();
        CulturalGoodRepositoryImpl culturalGoodRepository = new CulturalGoodRepositoryImpl();
        CulturalGoodApplicationService culturalGoodService = new CulturalGoodApplicationService(culturalGoodRepository);
        UI ui = new UI();
        CulturalGoodController controller = new CulturalGoodController(culturalGoodService, ui, authenticationService);

        System.out.println("--- Starting Cultural Good Insertion Use Case Simulation ---");

        // Scenario 1: Successful Insertion
        System.out.println("\n--- Scenario 1: Successful Insertion ---");
        runSuccessfulInsertionScenario(controller, ui);

        // Scenario 2: Invalid Data (Flow of Events 4)
        System.out.println("\n--- Scenario 2: Invalid Data ---");
        runInvalidDataScenario(controller, ui);

        // Scenario 3: Duplicate Cultural Good (Quality Requirement)
        System.out.println("\n--- Scenario 3: Duplicate Cultural Good ---");
        runDuplicateCulturalGoodScenario(controller, ui);

        // Scenario 4: Connection Interrupted (Exit Condition: Connection to ETOUR server)
        System.out.println("\n--- Scenario 4: Connection Interrupted ---");
        runConnectionInterruptedScenario(controller, ui, culturalGoodRepository);

        // Scenario 5: Agency Operator cancels (Exit Condition)
        System.out.println("\n--- Scenario 5: Agency Operator Cancels ---");
        runCancellationScenario(controller, ui);

        ui.closeScanner();
        System.out.println("\n--- Cultural Good Insertion Use Case Simulation Ended ---");
    }

    private static void runSuccessfulInsertionScenario(CulturalGoodController controller, UI ui) {
        // AO activates insert cultural good feature
        controller.showInsertForm(); // Checks authentication, displays form

        // AO fills and submits form with data (formData : CulturalGoodDTO)
        CulturalGoodDTO newCulturalGood = new CulturalGoodDTO(
                "Mona Lisa Painting",
                "A half-length portrait painting by Italian artist Leonardo da Vinci.",
                LocalDate.of(1503, 1, 1)
        );
        System.out.println("\n[AO] Submitting valid data: " + newCulturalGood.getName());
        String previewStatus = controller.submitCulturalGoodData(newCulturalGood);

        if ("CONFIRM".equals(previewStatus)) {
            // AO confirms operation
            if (ui.getConfirmation()) { // Simulate AO confirming
                System.out.println("[AO] Confirmed insertion.");
                String insertionStatus = controller.confirmInsertion(newCulturalGood);
                System.out.println("Insertion result: " + insertionStatus);
            } else {
                ui.notifyCancellation("Operation cancelled by user after preview.");
            }
        }
    }

    private static void runInvalidDataScenario(CulturalGoodController controller, UI ui) {
        controller.showInsertForm(); // Checks authentication, displays form

        // AO fills and submits form with invalid data (e.g., empty name)
        CulturalGoodDTO invalidCulturalGood = new CulturalGoodDTO(
                "", // Invalid: empty name
                "A description for an invalid good.",
                LocalDate.of(2023, 1, 1)
        );
        System.out.println("\n[AO] Submitting invalid data (empty name).");
        String previewStatus = controller.submitCulturalGoodData(invalidCulturalGood);
        System.out.println("Preview result for invalid data: " + previewStatus);
    }

    private static void runDuplicateCulturalGoodScenario(CulturalGoodController controller, UI ui) {
        // First, successfully insert a cultural good to create a duplicate
        CulturalGoodDTO firstGood = new CulturalGoodDTO(
                "The Starry Night",
                "An oil-on-canvas painting by Dutch Post-Impressionist painter Vincent van Gogh.",
                LocalDate.of(1889, 6, 1)
        );
        System.out.println("\n[AO] First, inserting 'The Starry Night' successfully.");
        controller.showInsertForm();
        String previewStatus1 = controller.submitCulturalGoodData(firstGood);
        if ("CONFIRM".equals(previewStatus1)) {
            if (ui.getConfirmation()) {
                controller.confirmInsertion(firstGood);
            }
        }

        // Now try to insert the same cultural good again (duplicate name)
        CulturalGoodDTO duplicateCulturalGood = new CulturalGoodDTO(
                "The Starry Night", // Duplicate name
                "Attempting to re-insert the same painting.",
                LocalDate.of(1889, 6, 1)
        );
        System.out.println("\n[AO] Submitting duplicate data: " + duplicateCulturalGood.getName());
        controller.showInsertForm();
        String previewStatus2 = controller.submitCulturalGoodData(duplicateCulturalGood);
        System.out.println("Preview result for duplicate data: " + previewStatus2);
    }

    private static void runConnectionInterruptedScenario(CulturalGoodController controller, UI ui, CulturalGoodRepositoryImpl repository) {
        controller.showInsertForm(); // Checks authentication, displays form

        CulturalGoodDTO goodForConnectionTest = new CulturalGoodDTO(
                "Ancient Scroll",
                "A very old and delicate manuscript.",
                LocalDate.of(100, 1, 1)
        );
        System.out.println("\n[AO] Submitting data for connection test: " + goodForConnectionTest.getName());
        String previewStatus = controller.submitCulturalGoodData(goodForConnectionTest);

        if ("CONFIRM".equals(previewStatus)) {
            if (ui.getConfirmation()) {
                // Simulate connection error
                System.out.println("[SYSTEM] Simulating database connection interruption...");
                repository.setSimulateConnectionError(true);

                System.out.println("[AO] Confirming insertion with simulated connection error.");
                String insertionStatus = controller.confirmInsertion(goodForConnectionTest);
                System.out.println("Insertion result: " + insertionStatus);

                // Reset connection error for subsequent tests
                repository.setSimulateConnectionError(false);
            } else {
                ui.notifyCancellation("Operation cancelled by user before connection issue.");
            }
        }
    }

    private static void runCancellationScenario(CulturalGoodController controller, UI ui) {
        controller.showInsertForm(); // Checks authentication, displays form

        CulturalGoodDTO goodForCancellation = new CulturalGoodDTO(
                "Roman Amphora",
                "Large ancient Roman vessel.",
                LocalDate.of(50, 5, 10)
        );
        System.out.println("\n[AO] Submitting data for potential cancellation: " + goodForCancellation.getName());
        String previewStatus = controller.submitCulturalGoodData(goodForCancellation);

        if ("CONFIRM".equals(previewStatus)) {
            // Simulate AO cancelling operation (AO -[#black]> UI: cancels operation)
            System.out.println(" (Simulating user cancellation after confirmation request: No for 'confirm', meaning cancelled for the operation)");
            ui.notifyCancellation("Operation cancelled by Agency Operator after reviewing details.");
            System.out.println("Operation result: CANCELLED");
            // No call to confirmInsertion if user cancels
        }
    }
}