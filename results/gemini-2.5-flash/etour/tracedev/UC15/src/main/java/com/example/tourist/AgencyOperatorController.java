package com.example.tourist;

import java.util.Scanner;

/**
 * Controller for the Agency Operator managing Tourist accounts.
 * Acts as an intermediary between the View and the Service layers.
 */
public class AgencyOperatorController {
    private final TouristManagementService touristManagementService;
    private final TouristFormView touristFormView;
    private TouristDTO currentTouristDTO; // Stores the DTO currently being viewed/edited
    private final AuthenticationService authenticationService;

    // For simulating user input in the console
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for AgencyOperatorController.
     *\n
     * @param service The TouristManagementService dependency.\n
     * @param view The TouristFormView dependency.\n
     * @param authService The AuthenticationService dependency.\n
     */
    public AgencyOperatorController(
            TouristManagementService service,
            TouristFormView view,
            AuthenticationService authService) {
        this.touristManagementService = service;
        this.touristFormView = view;
        this.authenticationService = authService;
        this.touristFormView.setController(this); // Set the controller reference in the view
    }

    /**
     * Handles the request to fetch and display tourist data.
     * Corresponds to `View -> Controller: requestTouristData(touristId)`
     *\n
     * @param touristId The ID of the tourist to retrieve.\n
     */
    public void requestTouristData(String touristId) {
        System.out.println("\n[AgencyOperatorController] Requesting tourist data for ID: " + touristId);

        if (!authenticationService.isLoggedIn()) {
            touristFormView.showLoginPrompt();
            return;
        }

        currentTouristDTO = touristManagementService.getTouristById(touristId);

        if (currentTouristDTO != null) {
            touristFormView.displayTouristData(currentTouristDTO);
        } else {
            touristFormView.showErrorMessage("Tourist with ID " + touristId + " not found.");
        }
    }

    /**
     * Handles the submission of modified tourist data from the view.
     * Corresponds to `View -> Controller: submitModifiedTouristData(editedTouristDTO)`
     *\n
     * @param editedTouristDTO The DTO containing the edited tourist data.\n
     */
    public void submitModifiedTouristData(TouristDTO editedTouristDTO) {
        System.out.println("\n[AgencyOperatorController] Submitting modified tourist data for ID: " + editedTouristDTO.id);

        // Store the edited DTO temporarily, so confirmUpdateOperation can access it
        this.currentTouristDTO = editedTouristDTO;

        ValidationResult validationResult = touristManagementService.validateTouristData(editedTouristDTO);

        if (validationResult.isValid()) {
            // Traceability for m21: validationResult : Success
            System.out.println("[AgencyOperatorController] Service returned: validationResult : Success");
            touristFormView.showConfirmationPrompt("Validation successful. Confirm changes? (yes/no)");
            // The view will call confirmUpdateOperation() if user confirms
        } else {
            // Traceability for m36: validationResult : Errors
            System.out.println("[AgencyOperatorController] Service returned: validationResult : Errors - " + validationResult.getErrors());
            touristFormView.showErrorMessage("Validation failed: " + validationResult.getErrors());
        }
    }

    /**
     * Handles the confirmation of an update operation by the user.
     * Corresponds to `View -> Controller: confirmUpdateOperation()`
     */
    public void confirmUpdateOperation() {
        System.out.println("[AgencyOperatorController] User confirmed update operation.");
        if (currentTouristDTO == null) {
            touristFormView.showErrorMessage("No tourist data to update or previous operation failed.");
            return;
        }

        try {
            TouristDTO updatedDTO = touristManagementService.saveTourist(currentTouristDTO);
            if (updatedDTO != null) {
                // Traceability for m33: updatedTouristDTO
                System.out.println("[AgencyOperatorController] Service returned: updatedTouristDTO for ID: " + updatedDTO.id);
                touristFormView.showSuccessMessage("Tourist data updated successfully for ID: " + updatedDTO.id);
                // Optionally refresh the displayed data with the newly saved data
                touristFormView.displayTouristData(updatedDTO);
            } else {
                System.err.println("[AgencyOperatorController] Service returned null for updatedTouristDTO.");
                touristFormView.showErrorMessage("Failed to update tourist data. Tourist not found or service issue.");
            }
        } catch (RuntimeException e) {
            // Catch specific exceptions if needed, here it catches the simulated connection error
            touristFormView.showErrorMessage("Error during update: " + e.getMessage());
        } finally {
             this.currentTouristDTO = null; // Clear DTO after operation
        }
    }

    // Main method to run a simulation of the interaction
    public static void main(String[] args) {
        // Setup the dependencies
        ITouristRepository touristRepository = new TouristRepository();
        TouristManagementService service = new TouristManagementService(touristRepository);
        AuthenticationService authService = new AuthenticationService();
        TouristFormView view = new TouristFormView(); // Create view first
        AgencyOperatorController controller = new AgencyOperatorController(service, view, authService);
        view.setController(controller); // Set controller on the view

        System.out.println("--- Starting Tourist Account Data Change Use Case Simulation ---");

        // Simulate Agency Operator login
        System.out.println("\nSimulating Agency Operator login...");
        if (!authService.login("operator", "password123")) {
            view.showLoginPrompt();
            return;
        }
        System.out.println("Agency Operator logged in: " + authService.isLoggedIn());

        // Step 1: Agency Operator requests data for a tourist
        String touristIdToFetch = "T001";
        controller.requestTouristData(touristIdToFetch);

        // Simulate user editing data and clicking submit
        System.out.println("\n--- Simulating User Editing Form ---");
        // The view's getEditedTouristData will return a modified DTO for T001
        TouristDTO editedDTO = view.getEditedTouristData();
        controller.submitModifiedTouristData(editedDTO);

        // Simulating the user confirmation for the valid case
        // In a real UI, this would be a button click after a prompt
        System.out.print("Simulating user confirmation for update (enter 'yes'): ");
        Scanner mainScanner = new Scanner(System.in);
        String confirmation = mainScanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation.trim())) {
            controller.confirmUpdateOperation();
        } else {
            System.out.println("Update operation cancelled by user.");
        }

        // --- Simulate Validation Failure ---
        System.out.println("\n--- Simulating Validation Failure ---");
        TouristDTO invalidDTO = new TouristDTO(editedDTO.id, "", editedDTO.surname, "invalid-email", editedDTO.address);
        System.out.println("Attempting to submit invalid data: " + invalidDTO);
        controller.submitModifiedTouristData(invalidDTO); // Should show error message directly

        // --- Simulate Connection Error on Save ---
        System.out.println("\n--- Simulating Connection Error during Save ---");
        // Reset original DTO with a slight change for the save to trigger connection error
        TouristDTO willFailDTO = new TouristDTO(editedDTO.id, "ConnectionTest", editedDTO.surname, editedDTO.email, editedDTO.address);
        ((TouristRepository)touristRepository).setSimulateConnectionError(true); // Enable error simulation

        controller.submitModifiedTouristData(willFailDTO);
        System.out.print("Simulating user confirmation for connection error test (enter 'yes'): ");
        confirmation = mainScanner.nextLine();
        if ("yes".equalsIgnoreCase(confirmation.trim())) {
            controller.confirmUpdateOperation(); // This should trigger the exception
        } else {
            System.out.println("Update operation cancelled for connection error test.");
        }
        ((TouristRepository)touristRepository).setSimulateConnectionError(false); // Disable error simulation

        mainScanner.close();
        System.out.println("\n--- Simulation Ended ---");
    }
}