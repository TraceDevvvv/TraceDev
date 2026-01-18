package com.example.justice.controller;

import com.example.justice.dto.JusticeUpdateDTO;
import com.example.justice.service.JusticeApplicationService;
import com.example.justice.validation.JusticeValidator;
import com.example.justice.domain.Justice;
import com.example.justice.view.JusticeUpdateFormView;

/**
 * Controller for administrative actions related to Justice records.
 * Handles requests from the view, orchestrates business logic via the application service,
 * and interacts with the validator.
 */
public class AdminController {
    private final JusticeApplicationService justiceApplicationService;
    private final JusticeValidator justiceValidator;
    private final JusticeUpdateFormView justiceUpdateFormView; // Reference to the view for callbacks

    /**
     * Constructs an AdminController with necessary dependencies.
     * Dependencies are injected to ensure loose coupling.
     *
     * @param service The application service for Justice-related business logic.
     * @param validator The validator for Justice update DTOs.
     * @param view The view to interact with for displaying messages and navigation.
     */
    public AdminController(JusticeApplicationService service, JusticeValidator validator, JusticeUpdateFormView view) {
        this.justiceApplicationService = service;
        this.justiceValidator = validator;
        this.justiceUpdateFormView = view;
    }

    /**
     * Handles the request to update a Justice record.
     * This method is the entry point for the "update Justice" use case from the UI perspective.
     *
     * @param justiceDto The DTO containing the updated justice information from the form.
     */
    public void updateJustice(JusticeUpdateDTO justiceDto) {
        System.out.println("AdminController: Received update request for Justice ID: " + justiceDto.getId());

        // Step 1: Validate the input DTO using the JusticeValidator.
        // This satisfies the quality requirement for data integrity.
        if (justiceValidator.validate(justiceDto)) {
            System.out.println("AdminController: Validation successful.");
            try {
                // Step 2: If validation passes, delegate the business logic to the application service.
                Justice updatedJustice = justiceApplicationService.updateJustice(justiceDto);

                if (updatedJustice != null) {
                    // Step 3: On successful update, inform the view.
                    justiceUpdateFormView.showSuccessMessage("Justification for ID " + updatedJustice.getId() + " has been modified successfully.");
                    justiceUpdateFormView.navigateToRegistryScreen(); // Redirect as per sequence diagram
                } else {
                    // Handle case where service could not find/update the justice
                    justiceUpdateFormView.showErrorMessage("Failed to update justice: Justice with ID " + justiceDto.getId() + " not found or update failed.");
                }
            } catch (Exception e) {
                // Catch any unexpected exceptions from the service layer
                justiceUpdateFormView.showErrorMessage("An unexpected error occurred during update: " + e.getMessage());
            }
        } else {
            // Step 4: If validation fails, inform the view with an error message.
            justiceUpdateFormView.showErrorMessage("Validation failed: Invalid date or other issue for Justice ID: " + justiceDto.getId() + ". Please check the input.");
        }
    }
}