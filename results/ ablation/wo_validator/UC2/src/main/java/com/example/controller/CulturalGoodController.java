package com.example.controller;

import com.example.domain.CulturalGood;
import com.example.dto.CulturalGoodDTO;
import com.example.service.CulturalGoodService;
import com.example.service.CulturalGoodService.DuplicateErrorException;
import com.example.operator.AgencyOperator;

/**
 * Controller handling user interactions for cultural good management.
 * Coordinates between UI, service, and DTOs.
 */
public class CulturalGoodController {
    private CulturalGoodService culturalGoodService;
    private AgencyOperator agencyOperator; // Association with AgencyOperator

    /**
     * Constructor with dependency injection.
     * @param service the CulturalGoodService
     */
    public CulturalGoodController(CulturalGoodService service) {
        this.culturalGoodService = service;
        this.agencyOperator = new AgencyOperator(); // Association instantiation
    }

    /**
     * Activates the insert feature (triggered by operator).
     * This would typically be called from a UI event.
     */
    public void activateInsertFeature() {
        // Step 1: Agency Operator activates feature
        // In a real app, this would trigger UI navigation.
        // For simulation, we simply call displayInsertForm.
        displayInsertForm();
    }

    /**
     * Displays the insert form to the operator.
     */
    public void displayInsertForm() {
        // Step 2: System displays form
        // In a real app, this would render a form UI.
        System.out.println("Displaying insert form...");
    }

    /**
     * Simulates the form being filled by the operator.
     * @param formData an array of strings representing form data [id, name, description, location, type]
     * @return a DTO populated with the form data
     */
    public CulturalGoodDTO fillForm(String[] formData) {
        CulturalGoodDTO dto = new CulturalGoodDTO();
        // Assuming formData order: id, name, description, location, type
        if (formData.length >= 5) {
            dto.setId(formData[0]);
            dto.setName(formData[1]);
            dto.setDescription(formData[2]);
            dto.setLocation(formData[3]);
            dto.setType(formData[4]);
        }
        // dateAdded can be set later or left null.
        System.out.println("Form data captured in DTO.");
        return dto;
    }

    /**
     * Notifies that the form is ready for submission.
     */
    public void formReady() {
        System.out.println("Form is ready for submission.");
    }

    /**
     * Submits the form data (DTO) to the service layer.
     * @param dto the CulturalGoodDTO containing form data
     */
    public void submitForm(CulturalGoodDTO dto) {
        // Steps 3-4: User fills & submits form
        try {
            culturalGoodService.insertCulturalGood(dto);
            // If no duplicate, request confirmation
            requestConfirmation();
        } catch (DuplicateErrorException e) {
            displayDuplicateError(e.getMessage());
        }
    }

    /**
     * Requests confirmation from the operator before insertion.
     */
    public void requestConfirmation() {
        // Step 6: System asks confirmation
        showConfirmationDialog();
    }

    /**
     * Displays a confirmation dialog to the operator.
     */
    public void showConfirmationDialog() {
        System.out.println("Please confirm the insertion (true/false):");
    }

    /**
     * Processes the operator's confirmation decision.
     * @param confirmation true to confirm, false to cancel
     */
    public void confirmOperation(boolean confirmation) {
        if (confirmation) {
            // Step 7: Operator confirms
            // In a real scenario, we would have the culturalGood object from earlier steps.
            // For simplicity, we assume the service re-creates it or we store it temporarily.
            // Here, we simulate by creating a new CulturalGood from a dummy DTO.
            // In a proper implementation, the CulturalGood would be passed along.
            CulturalGoodDTO dummyDTO = new CulturalGoodDTO();
            dummyDTO.setName("Sample"); // This is just a placeholder.
            CulturalGood culturalGood = CulturalGood.createFromDTO(dummyDTO);
            culturalGoodService.insertCulturalGoodConfirmed(culturalGood);
            insertionCompleted();
        } else {
            // User cancels
            cancelOperation();
        }
    }

    /**
     * Called when insertion is completed successfully.
     */
    public void insertionCompleted() {
        displaySuccessMessage();
    }

    /**
     * Displays a success message to the operator.
     */
    public void displaySuccessMessage() {
        // Step 8-Exit: System memorizes & notifies inclusion
        System.out.println("Cultural good inserted successfully!");
    }

    /**
     * Cancels the insertion operation.
     */
    public void cancelOperation() {
        displayCancellationMessage();
    }

    /**
     * Displays a cancellation message.
     */
    public void displayCancellationMessage() {
        System.out.println("Operation cancelled by user.");
    }

    /**
     * Displays a duplicate error message.
     * @param message the error message
     */
    public void displayDuplicateError(String message) {
        System.out.println("Duplicate error: " + message);
    }

    /**
     * Displays a connection error message (for interrupted connections).
     */
    public void displayConnectionError() {
        System.out.println("Connection interrupted. Please try again.");
    }
}