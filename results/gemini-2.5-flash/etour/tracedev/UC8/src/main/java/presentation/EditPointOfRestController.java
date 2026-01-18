package presentation;

import application.PointOfRestService;
import application.ErroredUseCase;
import dto.PointOfRestDTO;

/**
 * Presentation Layer: Controller component for editing a Point of Rest.
 * Mediates between the View and the Application Layer (Service).
 * Assumes authenticated user (R3).
 */
public class EditPointOfRestController {
    private PointOfRestService pointOfRestService;
    private EditPointOfRestView view;
    private ErroredUseCase erroredUseCase; // Added to satisfy requirement R7

    // Holds the DTO currently being edited, for confirmation and finalization
    private PointOfRestDTO currentEditedDTO;

    // Constructor for dependency injection
    public EditPointOfRestController(PointOfRestService pointOfRestService, EditPointOfRestView view, ErroredUseCase erroredUseCase) {
        this.pointOfRestService = pointOfRestService;
        this.view = view;
        this.erroredUseCase = erroredUseCase;
    }

    /**
     * Handles the selection of a Point of Rest by its ID.
     * Retrieves the details and displays them in the view.
     * @param id The ID of the Point of Rest to select.
     */
    public void selectPoint(String id) {
        System.out.println("[Controller] Selecting Point of Rest with ID: " + id);
        // Interaction: Controller -> Service: getPointOfRestDetails(id)
        PointOfRestDTO dto = pointOfRestService.getPointOfRestDetails(id);

        if (dto != null) {
            this.currentEditedDTO = dto; // Store for potential updates
            // Interaction: Controller -> View: displayForm(dto)
            view.displayForm(dto);
        } else {
            view.showError("Point of Rest with ID " + id + " not found.");
            erroredUseCase.activate("Point of Rest details could not be retrieved.");
        }
    }

    /**
     * Handles the submission of form data from the view.
     * Validates the data and prepares for an update, then prompts for confirmation.
     * @param dto The DTO containing the updated Point of Rest details.
     */
    public void submitFormData(PointOfRestDTO dto) {
        System.out.println("[Controller] Submitting form data for validation.");
        // Interaction: Controller -> Service: validateAndPrepareUpdate(dto)
        try {
            boolean isValid = pointOfRestService.validateAndPrepareUpdate(dto);
            if (isValid) {
                this.currentEditedDTO = dto; // Update the stored DTO with the validated data
                // Interaction: Controller -> View: showConfirmation()
                view.showConfirmation();
            } else {
                // Assuming validateAndPrepareUpdate might throw an exception or return false with an implicit error message
                view.showError("Invalid data provided for Point of Rest. Please check the form.");
                // Interaction: Controller -> ErroredUseCase: activate(errorMessage)
                erroredUseCase.activate("Validation failed for Point of Rest update.");
            }
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
            erroredUseCase.activate("Validation error: " + e.getMessage());
        }
    }

    /**
     * Handles the confirmation of changes by the user.
     * Finalizes the update process.
     */
    public void confirmChanges() {
        System.out.println("[Controller] User confirmed changes. Finalizing update...");
        // Q.R.: Block input controls immediately
        // Interaction: Controller -> View: blockInputs()
        view.blockInputs();

        if (currentEditedDTO == null) {
            view.showError("No data to confirm. Please select and edit a Point of Rest first.");
            view.unblockInputs();
            erroredUseCase.activate("Attempted to confirm changes without pending data.");
            return;
        }

        // Interaction: Controller -> Service: finalizePointOfRestUpdate(currentEditedDTO)
        try {
            boolean success = pointOfRestService.finalizePointOfRestUpdate(currentEditedDTO);
            if (success) {
                // Interaction: Controller -> View: showSuccess()
                view.showSuccess("Point of Rest updated successfully.");
            } else {
                // This branch might be reached if `finalizePointOfRestUpdate` failed internally without throwing
                view.showError("Failed to finalize Point of Rest update.");
                erroredUseCase.activate("Point of Rest update failed after validation.");
            }
        } catch (Exception e) { // Catch any potential exceptions from the service layer
            view.showError("An error occurred during update: " + e.getMessage());
            erroredUseCase.activate("Critical error during Point of Rest update: " + e.getMessage());
        } finally {
            // Interaction: Controller -> View: unblockInputs()
            view.unblockInputs();
            this.currentEditedDTO = null; // Clear pending DTO after operation
        }
    }

    /**
     * Handles the cancellation of changes by the user.
     * Navigates back to the previous screen.
     */
    public void cancelChanges() {
        System.out.println("[Controller] User canceled changes.");
        this.currentEditedDTO = null; // Clear any pending changes
        // Interaction: Controller -> View: navigateToPreviousScreen()
        view.navigateToPreviousScreen();
    }

    /**
     * Main method to demonstrate the use case flow.
     */
    public static void main(String[] args) {
        // Setup the dependencies (In a real app, this would be handled by a DI framework)
        infrastructure.DatabaseConnection dbConnection = new infrastructure.DatabaseConnection();
        infrastructure.PointOfRestRepository repository = new infrastructure.PointOfRestRepository(dbConnection);
        application.ErroredUseCase erroredUseCase = new application.ErroredUseCase(); // R7, R12
        application.PointOfRestService service = new application.PointOfRestService(repository, erroredUseCase);
        presentation.EditPointOfRestView view = new presentation.EditPointOfRestView(null); // Pass null for now, set below
        presentation.EditPointOfRestController controller = new presentation.EditPointOfRestController(service, view, erroredUseCase);
        // Now set the controller in the view, simulating circular dependency setup
        view = new presentation.EditPointOfRestView(controller); // Re-instantiate view with controller
        controller = new presentation.EditPointOfRestController(service, view, erroredUseCase); // Re-instantiate controller with correct view

        System.out.println("--- Starting Edit Point of Rest Use Case Simulation ---");

        // --- Scenario 1: Successful Edit ---
        System.out.println("\n--- Scenario 1: Successful Point of Rest Edit ---");
        String pointId = "POR001";
        // 1. AO -> View : selectPointOfRest(pointId: String)
        view.selectPointOfRest(pointId);

        // Simulate AO changing data
        PointOfRestDTO updatedDto = new PointOfRestDTO();
        updatedDto.id = pointId;
        updatedDto.name = "Updated Cafe Central";
        updatedDto.address = "123 New Central Ave, City";
        updatedDto.description = "A cozy cafe with improved facilities.";
        updatedDto.status = "Active";

        // 2. AO -> View : submitFormData(updatedDTO: PointOfRestDTO)
        view.submitFormData(updatedDto);

        // 3. AO -> View : confirmChanges()
        view.confirmChanges();

        // --- Scenario 2: Invalid Data Submission ---
        System.out.println("\n--- Scenario 2: Invalid Data Submission ---");
        String pointId2 = "POR002";
        view.selectPointOfRest(pointId2); // Select another point
        PointOfRestDTO invalidDto = new PointOfRestDTO();
        invalidDto.id = pointId2;
        invalidDto.name = ""; // Invalid name
        invalidDto.address = "Invalid Address";
        invalidDto.description = "Test invalid data";
        invalidDto.status = "Active";

        view.submitFormData(invalidDto); // This will trigger validation failure

        // --- Scenario 3: Cancel Operation ---
        System.out.println("\n--- Scenario 3: Cancel Operation ---");
        String pointId3 = "POR003";
        view.selectPointOfRest(pointId3); // Select another point
        PointOfRestDTO canceledDto = new PointOfRestDTO();
        canceledDto.id = pointId3;
        canceledDto.name = "Temporary Name";
        canceledDto.address = "Temporary Address";
        canceledDto.description = "This should not be saved.";
        canceledDto.status = "Active";

        view.submitFormData(canceledDto); // Validates, then asks for confirmation
        // Instead of confirm, AO cancels
        view.cancelChanges();

        // --- Scenario 4: Connection Interrupted ---
        System.out.println("\n--- Scenario 4: Connection Interrupted during save ---");
        String pointId4 = "POR004";
        // Force the repository to throw an exception for demonstration
        repository.setSimulateConnectionInterruption(true);

        view.selectPointOfRest(pointId4);
        PointOfRestDTO interruptDto = new PointOfRestDTO();
        interruptDto.id = pointId4;
        interruptDto.name = "Interrupted Update";
        interruptDto.address = "Interrupted Address";
        interruptDto.description = "Should not save due to connection error.";
        interruptDto.status = "Active";

        view.submitFormData(interruptDto);
        view.confirmChanges(); // This should trigger the ConnectionException
        repository.setSimulateConnectionInterruption(false); // Reset for other operations

        System.out.println("\n--- End of Use Case Simulation ---");
    }
}