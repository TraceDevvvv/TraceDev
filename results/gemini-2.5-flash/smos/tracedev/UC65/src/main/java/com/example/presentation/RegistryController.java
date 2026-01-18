package com.example.presentation;

import com.example.application.RegistryService;
import com.example.dataaccess.SMOSConnectionException;

/**
 * Controller layer responsible for handling user input from the RegistryView
 * and orchestrating business logic through the RegistryService.
 * Assumes the "viewelcoregistri" use case has been completed.
 *
 * This controller also addresses requirements R3, R4, R5 by checking preconditions
 * (though in this simulation, they are assumed true or hardcoded).
 */
public class RegistryController {
    private RegistryService registryService;
    private RegistryView registryView;
    private AuthenticationService authenticationService; // Added for R3
    private RegistrySelectionService registrySelectionService; // Added for R4
    private AcademicYearRegistryList academicYearRegistryList; // Added for R5

    /**
     * Constructs a RegistryController with necessary dependencies.
     * @param registryService The service for business logic.
     * @param registryView The view for displaying information.
     * @param authenticationService The service to check login status (R3).
     * @param registrySelectionService The service to get selected registry context (R4).
     * @param academicYearRegistryList The ViewModel to get available class IDs (R5).
     */
    public RegistryController(RegistryService registryService, RegistryView registryView,
                              AuthenticationService authenticationService,
                              RegistrySelectionService registrySelectionService,
                              AcademicYearRegistryList academicYearRegistryList) {
        this.registryService = registryService;
        this.registryView = registryView;
        this.authenticationService = authenticationService;
        this.registrySelectionService = registrySelectionService;
        this.academicYearRegistryList = academicYearRegistryList;
    }

    /**
     * Handles the request to view the detailed class registry.
     * This method implements the main flow of the "View Class Registry Details" sequence diagram.
     *
     * @param classId The ID of the class whose registry details are to be viewed.
     */
    public void viewClassRegistry(String classId) {
        System.out.println("\n[RegistryController] Received request to view class registry for classId: " + classId);
        // Note: Preconditions (R3, R4, R5) are handled by the caller (e.g., RegistryView.main)
        // or can be re-checked here if the controller should be fully responsible for them.
        // For this flow, we assume they are met before this method is called.

        try {
            // Step 1: Direction clicks "Register" button (simulated by method call parameter).
            // The view receives the event and delegates to the controller.

            // RegistryController -> RegistryService : getDetailedClassRegistry(classId)
            RegistryViewModel registryViewModel = registryService.getDetailedClassRegistry(classId);

            // Connection successful alternative path
            // RegistryController -> RegistryView : displayClassRegistry(registryViewModel)
            registryView.displayClassRegistry(registryViewModel);
            // RegistryView -> RegistryView : displayJustificationForm() (Step 7)
            registryView.displayJustificationForm();
            // RegistryView -> RegistryView : displayDisciplinaryNoteForm() (Step 8)
            registryView.displayDisciplinaryNoteForm();

            // Direction <-> RegistryView : reviewsDisplayedInformation() (Step 9)
            registryView.reviewsDisplayedInformation();

        } catch (SMOSConnectionException e) {
            // SMOS server connection interrupted alternative path
            // RegistryController -> RegistryController : handleSMOSConnectionError("Failed to retrieve data.") (R16)
            handleSMOSConnectionError("Failed to retrieve data due to SMOS connection issue.");
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("[RegistryController] An unexpected error occurred: " + e.getMessage());
            registryView.displayErrorMessage("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Handles errors related to SMOS connection failures.
     * This method is added to satisfy requirement R16.
     *
     * @param message A descriptive error message.
     */
    public void handleSMOSConnectionError(String message) {
        System.err.println("[RegistryController] Handling SMOS connection error: " + message);
        // RegistryController -> RegistryView : displayErrorMessage("Failed to connect to SMOS server.")
        registryView.displayErrorMessage("Failed to connect to SMOS server. " + message);
    }
}