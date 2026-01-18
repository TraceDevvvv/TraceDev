package ui;

import application.CulturalHeritageDTO;
import application.ModifyCulturalHeritageUseCaseController;
import application.OperationResult;
import errorhandling.ErroredUseCase;
import java.util.List;

/**
 * UI form for modifying Cultural Heritage.
 * Implements UI flow of events (trace Req5).
 * Precondition (Req4): Agency Operator must be logged in.
 * Blocks input upon confirmation to prevent multiple submissions (Req15/16).
 */
public class CulturalHeritageForm {
    private ModifyCulturalHeritageUseCaseController controller;
    private String currentItemId;
    private boolean isFormLocked;
    private boolean isOperationInProgress;
    private ErroredUseCase errorHandler; // Dependency for REQ-009
    private CulturalHeritageDTO currentFormData;

    public CulturalHeritageForm(ModifyCulturalHeritageUseCaseController controller) {
        this.controller = controller;
        this.isFormLocked = false;
        this.isOperationInProgress = false;
        this.errorHandler = new ErroredUseCase(); // In a real app, might be injected
        this.currentFormData = null;
    }

    /**
     * Displays a list of search results (simplified).
     */
    public void displayList(List<CulturalHeritageDTO> searchResults) {
        // In a real Swing/JavaFX app, this would update a table/list component.
        System.out.println("Displaying list of " + searchResults.size() + " items.");
    }

    /**
     * Called when user selects an item from the list (step 1 in sequence diagram).
     */
    public void onItemSelected(String id) {
        this.currentItemId = id;
        lockForm();
        loadAndDisplayItem();
    }

    /**
     * Loads the selected item and displays it in the form (step 2).
     */
    public void loadAndDisplayItem() {
        CulturalHeritageDTO dto = controller.loadCulturalHeritage(currentItemId);
        if (dto == null) {
            displayError("Item not found");
            unlockForm();
            return;
        }
        bindDataToForm(dto);
        unlockForm();
        // In a real UI, we would also update the UI to show the form enabled.
        System.out.println("Edit form displayed for item: " + dto.getName());
    }

    /**
     * Called when user submits changes (step 3).
     */
    public void onSubmitChanges() {
        lockForm();
        CulturalHeritageDTO modifiedData = collectDataFromForm();
        OperationResult result = controller.submitChanges(modifiedData);
        if (result.isSuccess) {
            displaySuccess("Please confirm transaction " + result.confirmationId);
            // In real UI, we would show a confirmation dialog with the confirmationId.
        } else {
            unlockForm();
            displayError(result.message);
            errorHandler.activateErrorFlow("Validation failed");
        }
    }

    /**
     * Called when user confirms the transaction (step 5).
     */
    public void onConfirmTransaction() {
        lockForm();
        isOperationInProgress = true; // Block input per REQ-015 and REQ-016
        // We need the confirmationId from previous step; for simplicity use currentItemId.
        OperationResult result = controller.confirmTransaction(currentItemId);
        if (result.isSuccess) {
            displaySuccess(result.message);
        } else {
            displayError(result.message);
            errorHandler.activateErrorFlow("Confirmation failed");
        }
        unlockForm();
        isOperationInProgress = false;
        // Exit condition: Notification of successful change
    }

    /**
     * Cancels the operation (alternative flow).
     */
    public void onCancel() {
        clearForm();
        unlockForm();
        isOperationInProgress = false;
        System.out.println("Operation cancelled, form cleared.");
    }

    /**
     * Locks the form to prevent user input.
     */
    public void lockForm() {
        isFormLocked = true;
        // In a real UI, disable input fields, buttons, etc.
    }

    /**
     * Unlocks the form to allow user input.
     */
    public void unlockForm() {
        isFormLocked = false;
        // In a real UI, enable input fields, buttons, etc.
    }

    /**
     * Displays an error message.
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
        // In a real UI, show in a dialog or status bar.
    }

    /**
     * Displays a success message.
     */
    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Binds DTO data to UI controls (simulated).
     */
    public void bindDataToForm(CulturalHeritageDTO dto) {
        currentFormData = dto;
        // In a real UI, populate text fields, comboboxes, etc.
        System.out.println("Form bound with data: " + dto.getName());
    }

    /**
     * Collects data from UI controls and creates a DTO.
     */
    public CulturalHeritageDTO collectDataFromForm() {
        // In a real UI, read values from UI controls
        CulturalHeritageDTO dto = new CulturalHeritageDTO();
        dto.setId(currentItemId);
        // For demonstration, assume we retrieve from currentFormData
        if (currentFormData != null) {
            dto.setName(currentFormData.getName());
            dto.setDescription(currentFormData.getDescription());
            dto.setType(currentFormData.getType());
            dto.setLocation(currentFormData.getLocation());
        }
        return dto;
    }

    /**
     * Clears the form.
     */
    public void clearForm() {
        currentFormData = null;
        currentItemId = null;
        // In a real UI, clear all input fields.
    }
}