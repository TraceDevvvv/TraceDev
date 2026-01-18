
package com.example.ui;

import com.example.dto.ConventionRequestDTO;
import com.example.controller.ConventionRequestController;
import com.example.dto.ConventionResponseDTO;

/**
 * Boundary class representing the convention request form.
 * Corresponds to the ConventionRequestForm class in the UML diagram.
 */
public class ConventionRequestForm {
    private ConventionRequestDTO requestData;
    private ConventionRequestController controller;

    /**
     * Default constructor. Initializes the form.
     * Assumption: Controller is injected or created elsewhere.
     */
    public ConventionRequestForm() {
        this.requestData = new ConventionRequestDTO();
        // Note: In a real application, the controller would be injected.
        // For simplicity, we create a default one with null dependencies.
        // This is a deviation from the UML to allow the code to compile.
        this.controller = new ConventionRequestController(null, null);
        System.out.println("ConventionRequestForm initialized.");
    }

    /**
     * Displays the data entry form.
     * In a real GUI application, this would render the form.
     */
    public void displayForm() {
        System.out.println("Displaying convention request form.");
    }

    /**
     * Populates the form with data from a DTO.
     * @param dto The DTO containing convention data.
     */
    public void populateForm(ConventionRequestDTO dto) {
        this.requestData = dto;
        System.out.println("Form populated with convention data.");
    }

    /**
     * Gets the submitted data from the form.
     * @return The ConventionRequestDTO containing the submitted data.
     */
    public ConventionRequestDTO getSubmittedData() {
        // In a real application, this would collect data from UI fields.
        System.out.println("Getting submitted data from form.");
        return requestData;
    }

    /**
     * Submits the form, triggering the request process.
     * This method corresponds to the sequence diagram step 4.
     */
    public void submitForm() {
        System.out.println("Form submitted.");
        ConventionRequestDTO dto = getSubmittedData();
        // According to sequence diagram, call controller's handleFormSubmission
        ConventionResponseDTO response = controller.handleFormSubmission(dto);
        // Show notification to operator (simplified)
        System.out.println("Notification: " + response.getMessage());
    }

    /**
     * Sets the controller for this form (for dependency injection).
     * @param controller The controller to set.
     */
    public void setController(ConventionRequestController controller) {
        this.controller = controller;
    }
}
