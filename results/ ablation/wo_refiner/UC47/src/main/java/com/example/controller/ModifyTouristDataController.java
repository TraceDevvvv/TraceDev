package com.example.controller;

import com.example.service.ITouristService;
import com.example.validation.IDataValidator;
import com.example.validation.ValidationResult;
import com.example.boundary.ErrorHandler;
import com.example.repository.ITouristRepository;
import com.example.dto.TouristDTO;
import java.util.List;

/**
 * Controller for tourist data modification.
 * Handles authentication, validation, confirmation, and persistence.
 */
public class ModifyTouristDataController {
    private ITouristRepository touristRepository;
    private ITouristService touristService;
    private IDataValidator validator;
    private ErrorHandler errorHandler;

    public ModifyTouristDataController(ITouristRepository touristRepository, ITouristService touristService, IDataValidator validator, ErrorHandler errorHandler) {
        this.touristRepository = touristRepository;
        this.touristService = touristService;
        this.validator = validator;
        this.errorHandler = errorHandler;
    }

    /**
     * Verify authentication of a user (REQ-004).
     */
    public boolean verifyAuthentication(String userId) {
        // Simplified: always return true for demo
        return true;
    }

    /**
     * Retrieve tourist data for a given user ID.
     */
    public TouristDTO getTouristData(String userId) {
        return touristService.loadTouristData(userId);
    }

    /**
     * Validate tourist data using the validator.
     */
    public ValidationResult validateTouristData(TouristDTO data) {
        // Could also delegate to service
        return validator.validate(data);
    }

    /**
     * Request confirmation of modified data (REQ-010).
     */
    public TouristDTO requestConfirmation(TouristDTO data) {
        // Simply return the data for confirmation
        return data;
    }

    /**
     * Confirm and save the modifications.
     */
    public boolean confirmModification(TouristDTO data) {
        try {
            return touristService.saveTouristData(data);
        } catch (Exception e) {
            errorHandler.handleConnectionError(e);
            return false;
        }
    }

    /**
     * Cancel the modification process (REQ-013).
     */
    public void cancelModification(String userId) {
        // Clean up any temporary data
        System.out.println("Cancelled modification for user: " + userId);
    }

    /**
     * Handle data validation errors (REQ-009).
     */
    public void handleDataError(List<String> errors) {
        errorHandler.handleValidationError(errors);
    }

    /**
     * Save tourist data (directly).
     */
    public boolean saveTouristData(TouristDTO data) {
        return confirmModification(data);
    }
}