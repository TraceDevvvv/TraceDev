package com.example.controller;

import com.example.dto.ConventionRequestDTO;
import com.example.dto.ConventionResponseDTO;
import com.example.service.ConventionValidationService;
import com.example.service.ConventionService;
import com.example.dto.ValidationResult;
import java.util.Scanner;

/**
 * Controller class for handling convention requests.
 * Corresponds to the ConventionRequestController class in the UML diagram.
 */
public class ConventionRequestController {
    private ConventionValidationService validationService;
    private ConventionService conventionService;

    /**
     * Constructor with dependencies.
     * @param validationService The validation service.
     * @param conventionService The convention service.
     */
    public ConventionRequestController(ConventionValidationService validationService,
                                       ConventionService conventionService) {
        this.validationService = validationService;
        this.conventionService = conventionService;
    }

    /**
     * Enables the request functionality (step 1 in sequence diagram).
     * This method would typically be called to initialize the UI.
     */
    public void enableRequestFunctionality() {
        System.out.println("Request functionality enabled.");
    }

    /**
     * Handles form submission (step 5 in sequence diagram).
     * @param requestDTO The DTO containing request data.
     * @return The response DTO.
     */
    public ConventionResponseDTO handleFormSubmission(ConventionRequestDTO requestDTO) {
        System.out.println("Controller handling form submission.");
        // Step: Validate request data
        ValidationResult result = validationService.validateRequestData(requestDTO);

        if (!result.getIsValid()) {
            // Validation failed: activate errored use case (step 6)
            System.out.println("Validation failed: " + result.getErrors());
            ConventionResponseDTO errorResponse = new ConventionResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Validation failed: " + String.join(", ", result.getErrors()));
            return errorResponse;
        } else {
            // Validation successful: ask for confirmation (step 7)
            System.out.println("Validation successful. Asking for confirmation.");
            boolean confirmed = requestConfirmation();
            if (confirmed) {
                confirmOperation();
                // Process the convention request
                return conventionService.processConventionRequest(requestDTO);
            } else {
                ConventionResponseDTO cancelResponse = new ConventionResponseDTO();
                cancelResponse.setSuccess(false);
                cancelResponse.setMessage("Operation cancelled by operator.");
                return cancelResponse;
            }
        }
    }

    /**
     * Requests confirmation from the operator (step 7).
     * In a real application, this would be a UI dialog.
     * @return true if confirmed, false otherwise.
     */
    public boolean requestConfirmation() {
        System.out.println("Request confirmation from operator (simulated).");
        // Simulating operator confirmation via console for demonstration.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Confirm operation? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes");
    }

    /**
     * Confirms the operation (step 8).
     * This method is called after the operator confirms.
     */
    public void confirmOperation() {
        System.out.println("Operation confirmed.");
    }
}