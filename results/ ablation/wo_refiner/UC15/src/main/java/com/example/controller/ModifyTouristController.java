package com.example.controller;

import com.example.dto.TouristDTO;
import com.example.entity.Tourist;
import com.example.repository.ITouristRepository;
import com.example.validation.ValidationResult;
import com.example.validation.ITouristValidator;
import com.example.ui.ErrorHandler;
import com.example.ui.AgencyOperatorUI;

/**
 * Orchestrates the flow of events per REQ-005.
 * Added triggerErroredUseCase method consolidated from handleValidationError 
 * and activateErroredUseCase per REQ-009.
 */
public class ModifyTouristController {
    private ITouristValidator validator;
    private ITouristRepository repository;
    private ErrorHandler errorHandler;
    private ErroredController erroredController;
    private AgencyOperatorUI agencyOperatorUI;
    
    public ModifyTouristController(ITouristValidator validator, ITouristRepository repository) {
        this.validator = validator;
        this.repository = repository;
        this.errorHandler = new ErrorHandler();
        this.erroredController = new ErroredController();
        this.agencyOperatorUI = new AgencyOperatorUI();
    }
    
    public ModifyTouristController(ITouristValidator validator, ITouristRepository repository, 
                                  ErrorHandler errorHandler, ErroredController erroredController) {
        this.validator = validator;
        this.repository = repository;
        this.errorHandler = errorHandler;
        this.erroredController = erroredController;
        this.agencyOperatorUI = new AgencyOperatorUI();
    }
    
    /**
     * Main method to modify tourist account.
     */
    public void modifyTouristAccount(String touristId, TouristDTO modifiedData) {
        System.out.println("Modifying tourist account: " + touristId);
        
        // Step 1: Find tourist
        Tourist tourist = repository.findById(touristId);
        if (tourist == null) {
            errorHandler.handleError("Tourist not found with ID: " + touristId);
            triggerErroredUseCase();
            return;
        }
        
        // Step 2: Apply modifications
        TouristDTO currentData = new TouristDTO(
            tourist.getTouristId(),
            tourist.getName(),
            tourist.getEmail(),
            tourist.getPhone(),
            tourist.getAddress()
        );
        currentData.applyModifications(modifiedData);
        
        // Step 3: Validate data
        ValidationResult validationResult = validateTouristData(currentData);
        
        if (!validationResult.getIsValid()) {
            // Handle validation error
            errorHandler.handleError("Invalid tourist data");
            errorHandler.displayErrorDetails(validationResult.getErrors());
            triggerErroredUseCase();
            return;
        }
        
        // Step 4: Confirm modification (simulated)
        boolean confirmed = confirmModification(currentData);
        if (!confirmed) {
            System.out.println("Modification cancelled by user");
            return;
        }
        
        // Step 5: Update entity and save
        tourist.updateFromDTO(currentData);
        repository.save(tourist);
        
        System.out.println("Tourist account modified successfully");
    }
    
    /**
     * Validates tourist data.
     */
    protected ValidationResult validateTouristData(TouristDTO touristData) {
        if (validator == null) {
            ValidationResult result = new ValidationResult();
            result.addError("Validator not initialized");
            return result;
        }
        return validator.validate(touristData);
    }
    
    /**
     * Confirms modification with user (simulated).
     */
    protected boolean confirmModification(TouristDTO touristData) {
        // In real implementation, this would show a confirmation dialog
        System.out.println("Confirm modification for tourist: " + touristData.getTouristId());
        return true; // Assume confirmed for simulation
    }
    
    /**
     * Consolidated method to trigger errored use case.
     * Per REQ-009.
     */
    public void triggerErroredUseCase() {
        System.out.println("Triggering errored use case");
        erroredController.activate();
    }
    
    // Methods for sequence diagram implementation
    
    public TouristDTO getModifiedTouristData() {
        return agencyOperatorUI.getModifiedTouristData();
    }
    
    public void displayTouristForm(TouristDTO touristDTO) {
        agencyOperatorUI.displayTouristForm(touristDTO);
    }
    
    public boolean displayConfirmationPrompt(TouristDTO modifiedData) {
        return agencyOperatorUI.displayConfirmationPrompt(modifiedData);
    }
    
    public void displaySuccessMessage() {
        agencyOperatorUI.displaySuccessMessage();
    }
    
    // Getters and setters for dependency injection
    public ITouristValidator getValidator() {
        return validator;
    }
    
    public void setValidator(ITouristValidator validator) {
        this.validator = validator;
    }
    
    public ITouristRepository getRepository() {
        return repository;
    }
    
    public void setRepository(ITouristRepository repository) {
        this.repository = repository;
    }
    
    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }
    
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
    
    public AgencyOperatorUI getAgencyOperatorUI() {
        return agencyOperatorUI;
    }
    
    public void setAgencyOperatorUI(AgencyOperatorUI agencyOperatorUI) {
        this.agencyOperatorUI = agencyOperatorUI;
    }
}