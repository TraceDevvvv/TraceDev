package com.example.service;

import com.example.dto.RefreshmentDTO;
import com.example.model.Refreshment;
import com.example.repository.IRefreshmentRepository;
import com.example.adapter.ETOURServerAdapter;

/**
 * Main service for managing refreshment point operations.
 */
public class RefreshmentPointManagementService {
    private IRefreshmentRepository refreshmentRepository;
    private ValidationService validationService;
    private NotificationService notificationService;
    private ETOURServerAdapter etourServerAdapter;

    // Constructor for dependency injection
    public RefreshmentPointManagementService(IRefreshmentRepository refreshmentRepository,
                                             ValidationService validationService,
                                             NotificationService notificationService,
                                             ETOURServerAdapter etourServerAdapter) {
        this.refreshmentRepository = refreshmentRepository;
        this.validationService = validationService;
        this.notificationService = notificationService;
        this.etourServerAdapter = etourServerAdapter;
    }

    /**
     * Retrieves a refreshment point by ID and returns as DTO.
     */
    public RefreshmentDTO getRefreshmentPoint(String pointId) {
        Refreshment refreshment = refreshmentRepository.findById(pointId);
        return new RefreshmentDTO(refreshment);
    }

    /**
     * Updates a refreshment point with data from DTO.
     * Follows sequence diagram flow.
     */
    public void updateRefreshmentPoint(RefreshmentDTO dto, String operatorId) {
        // Step 1: Validate data
        boolean isValid = validationService.validateRefreshmentData(dto);
        if (!isValid) {
            // Validation failed - throw exception or return error
            throw new IllegalArgumentException("Validation failed for refreshment data");
        }

        // Step 2: Check connection to ETOUR server
        boolean connectionAvailable = etourServerAdapter.checkConnection();
        if (!connectionAvailable) {
            throw new RuntimeException("Server connection lost");
        }

        // Step 3: Send update confirmation to server
        boolean confirmationReceived = etourServerAdapter.sendUpdateConfirmation(dto);
        if (!confirmationReceived) {
            throw new RuntimeException("Server confirmation not received");
        }

        // Step 4: At this point, sequence diagram shows operator confirmation is requested
        // The actual operator confirmation is handled by the RestaurantPointOperator class
        // This method assumes operator has confirmed, so we proceed to save

        // Step 5: Save to repository
        Refreshment refreshment = dto.toEntity();
        refreshmentRepository.save(refreshment);

        // Step 6: Send success notification
        notificationService.sendSuccessNotification(operatorId, "Data updated successfully");
    }

    /**
     * Validates data using ValidationService.
     * Public method as per class diagram.
     */
    public boolean validateData(RefreshmentDTO dto) {
        return validationService.validateRefreshmentData(dto);
    }

    /**
     * Requests server confirmation (private method as per class diagram).
     * This is a helper that combines checkConnection and sendUpdateConfirmation.
     */
    private boolean requestServerConfirmation() {
        // This method is kept private as per class diagram.
        // In real implementation, would coordinate with ETOUR server.
        return etourServerAdapter.checkConnection();
    }
}