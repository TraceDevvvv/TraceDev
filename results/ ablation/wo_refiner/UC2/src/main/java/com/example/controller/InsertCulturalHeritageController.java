package com.example.controller;

import com.example.heritage.CulturalGood;
import com.example.heritage.HeritageType;
import com.example.ui.model.FormData;
import com.example.ui.model.ValidationResult;
import com.example.ui.model.OperationResult;
import com.example.repository.CulturalGoodRepository;
import com.example.error.ErrorHandler;
import com.example.exception.ConnectionException;
import com.example.connection.ConnectionManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for inserting cultural heritage.
 * Implements logic from sequence diagram.
 */
public class InsertCulturalHeritageController {
    private CulturalGoodRepository culturalGoodRepository;
    private ErrorHandler errorHandler;
    private ConnectionManager connectionManager;
    
    // Temporary storage for a pending cultural good (for confirmation).
    private CulturalGood pendingCulturalGood;
    
    public InsertCulturalHeritageController(CulturalGoodRepository culturalGoodRepository, 
                                            ErrorHandler errorHandler,
                                            ConnectionManager connectionManager) {
        this.culturalGoodRepository = culturalGoodRepository;
        this.errorHandler = errorHandler;
        this.connectionManager = connectionManager;
    }
    
    /**
     * Handles the insert request from UI.
     * @param formData the form data submitted
     * @return operation result
     */
    public OperationResult handleInsertRequest(FormData formData) {
        // 1. Validate data.
        ValidationResult validationResult = validateData(formData);
        if (!validationResult.isValid()) {
            errorHandler.handleValidationError(validationResult);
            // Return validation result for UI to display errors.
            return new OperationResult(false, "Validation failed", null);
        }
        
        // 2. Convert FormData to CulturalGood.
        HeritageType type;
        try {
            type = HeritageType.valueOf(formData.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            return new OperationResult(false, "Invalid heritage type", null);
        }
        CulturalGood culturalGood = new CulturalGood(formData.getName(), 
                formData.getDescription(), type, formData.getLocation());
        
        // 3. Check for duplicates.
        boolean duplicate = checkForDuplicates(culturalGood);
        if (duplicate) {
            errorHandler.handleDuplicateError(culturalGood);
            return new OperationResult(false, "Duplicate found", null);
        }
        
        // 4. Store as pending for confirmation.
        pendingCulturalGood = culturalGood;
        
        // Return pending result, indicating confirmation needed.
        return new OperationResult(false, "Confirmation required", null);
    }
    
    /**
     * Validates the form data.
     * @param formData the data to validate
     * @return validation result
     */
    private ValidationResult validateData(FormData formData) {
        List<String> errors = new ArrayList<>();
        if (formData.getName() == null || formData.getName().trim().isEmpty()) {
            errors.add("Name is required");
        }
        if (formData.getDescription() == null || formData.getDescription().trim().isEmpty()) {
            errors.add("Description is required");
        }
        if (formData.getType() == null || formData.getType().trim().isEmpty()) {
            errors.add("Type is required");
        } else {
            try {
                HeritageType.valueOf(formData.getType().toUpperCase());
            } catch (IllegalArgumentException e) {
                errors.add("Invalid heritage type");
            }
        }
        if (formData.getLocation() == null || formData.getLocation().trim().isEmpty()) {
            errors.add("Location is required");
        }
        return new ValidationResult(errors.isEmpty(), errors);
    }
    
    /**
     * Check for duplicates by name and location.
     * Prevents duplicate cultural heritage entries (Req 16).
     * @param culturalGood the cultural good to check
     * @return true if duplicate exists, false otherwise
     */
    private boolean checkForDuplicates(CulturalGood culturalGood) {
        CulturalGood[] existing = culturalGoodRepository.findByNameAndLocation(
                culturalGood.getName(), culturalGood.getLocation());
        return existing.length > 0;
    }
    
    /**
     * Called when operator confirms the operation.
     * @return operation result
     */
    public OperationResult confirmationReceived() {
        if (pendingCulturalGood == null) {
            return new OperationResult(false, "No pending cultural good", null);
        }
        
        // Check connection.
        if (!connectionManager.testConnection()) {
            ConnectionException ex = new ConnectionException("Connection interrupted", 503, new java.util.Date());
            errorHandler.handleConnectionError(ex);
            return new OperationResult(false, "Connection error", null);
        }
        
        // Persist the cultural good.
        boolean saved = persistCulturalGood(pendingCulturalGood);
        if (saved) {
            OperationResult result = new OperationResult(true, "Insertion successful", pendingCulturalGood.getId());
            pendingCulturalGood = null;
            return result;
        } else {
            return new OperationResult(false, "Persistence failed", null);
        }
    }
    
    /**
     * Persists the cultural good.
     * @param culturalGood the cultural good to persist
     * @return true if successful, false otherwise
     */
    private boolean persistCulturalGood(CulturalGood culturalGood) {
        try {
            return culturalGoodRepository.save(culturalGood);
        } catch (ConnectionException e) {
            errorHandler.handleConnectionError(e);
            return false;
        }
    }
    
    /**
     * Called when operator cancels the request.
     * Restructured cancellation flow (Req 013).
     * @return operation result
     */
    public OperationResult cancelRequest() {
        pendingCulturalGood = null;
        return new OperationResult(false, "Operation cancelled", null);
    }
    
    // Missing methods from sequence diagram messages.
    public void displayValidationErrors(ValidationResult validationResult) {
        // Forward to UI to display validation errors.
    }
    
    public void displayDuplicateError() {
        // Forward to UI to display duplicate error.
    }
    
    public void displayConfirmationRequest() {
        // Forward to UI to display confirmation request.
    }
    
    public void verifyDataAndAskForConfirmation() {
        // Forward to UI to verify data and ask for confirmation.
    }
    
    public void persistNewCulturalGood() {
        // This is done by the repository. This method may not be needed as persistence is handled in confirmationReceived.
    }
}