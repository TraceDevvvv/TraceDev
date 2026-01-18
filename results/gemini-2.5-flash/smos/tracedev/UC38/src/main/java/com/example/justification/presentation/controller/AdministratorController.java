
package com.example.justification.presentation.controller;

import com.example.justification.application.dto.JustificationDetailsDto;
import com.example.justification.application.dto.ModifyJustificationRequestDto;
import com.example.justification.application.port.in.EliminateJustificationUseCase;
import com.example.justification.application.port.in.ModifyJustificationUseCase;
import com.example.justification.application.port.in.ViewJustificationDetailsUseCase;
import com.example.justification.application.port.out.IJustificationRepository;
import com.example.justification.application.service.JustificationService;
import com.example.justification.infrastructure.repository.JustificationRepositoryImpl;

/**
 * Controller for Administrator-related actions on justifications.
 * Precondition: Administrator is logged in (R3) and has navigated from 'ViewingLancogiustifies' (R4).
 * This controller acts as the entry point for UI requests related to justification management.
 */
public class AdministratorController {

    private final ViewJustificationDetailsUseCase viewUseCase;
    private final ModifyJustificationUseCase modifyUseCase;
    private final EliminateJustificationUseCase eliminateUseCase;

    /**
     * Constructs an AdministratorController with the necessary use case interfaces.
     * These use cases are injected to decouple the controller from specific service implementations.
     *
     * @param view       The use case for viewing justification details.
     * @param modify     The use case for modifying justifications.
     * @param eliminate  The use case for eliminating justifications.
     */
    public AdministratorController(
            ViewJustificationDetailsUseCase view,
            ModifyJustificationUseCase modify,
            EliminateJustificationUseCase eliminate) {
        this.viewUseCase = view;
        this.modifyUseCase = modify;
        this.eliminateUseCase = eliminate;
    }

    /**
     * Handles the request to view details of a specific justification.
     * This method orchestrates the interaction between the UI and the application's view use case.
     *
     * @param id The unique identifier of the justification to view.
     * @return A {@link JustificationDetailsDto} containing the justification's details.
     */
    public JustificationDetailsDto viewJustification(String id) {
        System.out.println("[Controller] -> [UseCase]: execute(id=" + id + ")");
        JustificationDetailsDto dto = viewUseCase.execute(id);
        System.out.println("[UseCase] --> [Controller]: justificationDetailsDto");
        return dto;
    }

    /**
     * Handles the request to modify an existing justification.
     *
     * @param id      The unique identifier of the justification to modify.
     * @param request A {@link ModifyJustificationRequestDto} containing the updated details.
     */
    public void modifyJustification(String id, ModifyJustificationRequestDto request) {
        System.out.println("[Controller] -> [UseCase]: execute(id=" + id + ", details=" + request.details + ", status=" + request.status + ")");
        modifyUseCase.execute(id, request);
        System.out.println("[UseCase] --> [Controller]: Modification complete");
    }

    /**
     * Handles the request to eliminate (delete) a justification.
     *
     * @param id The unique identifier of the justification to eliminate.
     */
    public void eliminateJustification(String id) {
        System.out.println("[Controller] -> [UseCase]: execute(id=" + id + ")");
        eliminateUseCase.execute(id);
        System.out.println("[UseCase] --> [Controller]: Elimination complete");
    }

    // Dummy UI class to simulate the Administrator UI for demonstration
    // In a real application, this would be a web component or a desktop UI class.
    public static class AdministratorUI {
        private AdministratorController controller;

        public AdministratorUI(AdministratorController controller) {
            this.controller = controller;
        }

        public void selectJustification(String justiceId) {
            System.out.println("\n[Admin] -> [UI]: selectJustification(\"" + justiceId + "\")");
            // Administrator selects a justification (related to a "green absence") to view its details. (m2)
            System.out.println("[UI] Note: Administrator selects a justification (related to a \"green absence\") to view its details.");
            // Precondition: Administrator is logged in (R3) and has selected a justification from a list (R4).

            System.out.println("[UI] -> [Controller]: viewJustification(\"" + justiceId + "\")");
            JustificationDetailsDto details = controller.viewJustification(justiceId);

            System.out.println("[Controller] --> [UI]: justificationDetailsDto");
            displayJustificationDetailsForm(details);
            // Displayed information is accurate and current (R10)
            // System displays a form with details and options to modify or eliminate the justification. (m15)
            System.out.println("[UI] Note: System displays a form with details and options to modify or eliminate the justification.");
        }

        public void displayJustificationDetailsForm(JustificationDetailsDto dto) {
            System.out.println("[UI] --> [Admin]: displayJustificationDetailsForm(justificationDetailsDto)");
            if (dto != null) {
                System.out.println("--- Justification Details ---");
                System.out.println("ID: " + dto.id);
                System.out.println("Details: " + dto.details);
                System.out.println("Status: " + dto.status);
                System.out.println("Absence ID: " + dto.absenceId);
                System.out.println("---------------------------\n");
            } else {
                System.out.println("No justification details to display.");
            }
        }

        public void modifySelectedJustification(String id, String newDetails, String newStatus) {
            System.out.println("[Admin] -> [UI]: modifyJustification(\"" + id + "\", \"" + newDetails + "\", \"" + newStatus + "\")");
            ModifyJustificationRequestDto request = new ModifyJustificationRequestDto(newDetails, newStatus);
            controller.modifyJustification(id, request);
            System.out.println("[UI] --> [Admin]: Justification " + id + " modified successfully.");
        }

        public void eliminateSelectedJustification(String id) {
            System.out.println("[Admin] -> [UI]: eliminateJustification(\"" + id + "\")");
            controller.eliminateJustification(id);
            System.out.println("[UI] --> [Admin]: Justification " + id + " eliminated successfully.");
        }
    }

    // Main method to demonstrate the sequence diagram flow
    public static void main(String[] args) {
        // 1. Setup the infrastructure (Repository)
        IJustificationRepository repository = new JustificationRepositoryImpl();

        // 2. Setup the application serv (Use Cases)
        // In a real application, JustificationService would implement these interfaces.
        // For this compilation fix, we assume it does and cast it.
        JustificationService justificationService = new JustificationService(repository);

        // 3. Setup the presentation layer (Controller)
        AdministratorController controller = new AdministratorController(
                (ViewJustificationDetailsUseCase) justificationService, // ViewJustificationDetailsUseCase
                (ModifyJustificationUseCase) justificationService, // ModifyJustificationUseCase
                (EliminateJustificationUseCase) justificationService  // EliminateJustificationUseCase
        );

        // 4. Simulate the Administrator UI
        AdministratorUI ui = new AdministratorUI(controller);

        System.out.println("--- Simulating Administrator views details of a justice ---");
        ui.selectJustification("J123");

        System.out.println("\n--- Simulating Administrator modifies a justice ---");
        ui.modifySelectedJustification("J123", "Medical leave for 3 days", "Approved");
        ui.selectJustification("J123"); // View again to confirm modification

        System.out.println("\n--- Simulating Administrator tries to view a non-existent justice ---");
        try {
            ui.selectJustification("J999");
        } catch (IllegalArgumentException e) {
            System.out.println("[UI] -> [Admin]: Error - " + e.getMessage());
        }

        System.out.println("\n--- Simulating Administrator eliminates a justice ---");
        ui.eliminateSelectedJustification("J124");
        try {
            ui.selectJustification("J124"); // Try to view eliminated justice
        } catch (IllegalArgumentException e) {
            System.out.println("[UI] -> [Admin]: Error - " + e.getMessage());
        }
    }
}
