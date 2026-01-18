package com.example.application;

import com.example.presentation.PointRestaurantBoundary;
import com.example.dto.RefreshmentDTO;
import com.example.authentication.AuthenticationService;
import com.example.domain.Refreshment;
import com.example.data.RefreshmentRepository;
import com.example.service.RefreshmentService;
import com.example.service.ValidationResult;

/**
 * Controller that orchestrates the update refreshment use case.
 */
public class UpdateRefreshmentController {
    private RefreshmentRepository refreshmentRepository;
    private RefreshmentService refreshmentService;
    private AuthenticationService authenticationService;

    public UpdateRefreshmentController(RefreshmentRepository refreshmentRepository, 
                                       RefreshmentService refreshmentService,
                                       AuthenticationService authenticationService) {
        this.refreshmentRepository = refreshmentRepository;
        this.refreshmentService = refreshmentService;
        this.authenticationService = authenticationService;
    }

    /**
     * Executes the main flow: loads data and initiates update process.
     */
    public void execute() {
        // For demonstration, using a hard-coded refreshment ID.
        Long refreshmentId = 1L;
        RefreshmentDTO dto = loadRefreshmentData(refreshmentId);
        // In a real scenario, the boundary would display the data and collect user input.
        // Here we simulate the boundary getting input.
        PointRestaurantBoundary boundary = new PointRestaurantBoundary();
        boundary.displayRefreshmentData(dto);
        RefreshmentDTO updatedDto = boundary.getRefreshmentFormInput();
        validateAndUpdate(updatedDto);
    }

    /**
     * Loads refreshment data by ID and maps to DTO.
     * @param refreshmentId the ID of the refreshment
     * @return the DTO containing refreshment data
     */
    public RefreshmentDTO loadRefreshmentData(Long refreshmentId) {
        Refreshment refreshment = refreshmentRepository.findById(refreshmentId);
        if (refreshment == null) {
            throw new IllegalArgumentException("Refreshment not found with ID: " + refreshmentId);
        }
        return mapToDTO(refreshment);
    }

    /**
     * Validates the DTO and updates the refreshment if valid.
     * @param refreshmentDTO the DTO with updated data
     * @return true if update succeeded, false otherwise
     */
    public Boolean validateAndUpdate(RefreshmentDTO refreshmentDTO) {
        ValidationResult validationResult = refreshmentService.validateRefreshmentData(refreshmentDTO);
        if (validationResult.isValid()) {
            PointRestaurantBoundary boundary = new PointRestaurantBoundary();
            boolean confirmed = boundary.showConfirmationPrompt();
            if (confirmed) {
                confirmChanges();
                Boolean success = refreshmentService.updateRefreshment(refreshmentDTO);
                if (success) {
                    boundary.showSuccessNotification();
                } else {
                    boundary.showErrorNotification("Connection to server lost");
                }
                return success;
            } else {
                cancelOperation();
                boundary.showErrorNotification("Operation cancelled");
                return false;
            }
        } else {
            PointRestaurantBoundary boundary = new PointRestaurantBoundary();
            String errorMsg = String.join(", ", validationResult.getErrorMessages());
            boundary.showErrorNotification(errorMsg);
            return false;
        }
    }

    /**
     * Called when the user confirms changes.
     */
    public void confirmChanges() {
        System.out.println("Changes confirmed.");
    }

    /**
     * Called when the user cancels the operation.
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled, cleaning up resources.");
    }

    /**
     * Maps a domain Refreshment to a RefreshmentDTO (added to satisfy consistency requirement).
     * @param refreshment the domain entity
     * @return the corresponding DTO
     */
    public RefreshmentDTO mapToDTO(Refreshment refreshment) {
        RefreshmentDTO dto = new RefreshmentDTO();
        dto.setId(refreshment.getId());
        dto.setName(refreshment.getName());
        dto.setDescription(refreshment.getDescription());
        dto.setPrice(refreshment.getPrice());
        dto.setAvailable(refreshment.isAvailable());
        return dto;
    }

    /**
     * Handles confirmation received from the boundary (from sequence diagram).
     */
    public void confirmationReceived() {
        System.out.println("Confirmation received, proceeding with update.");
    }

    /**
     * Handles operation cancelled from the boundary (from sequence diagram).
     */
    public void operationCancelled() {
        cancelOperation();
    }
}