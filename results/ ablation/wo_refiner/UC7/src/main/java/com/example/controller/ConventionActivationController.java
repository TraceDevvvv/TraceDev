
package com.example.controller;

import com.example.model.Convention;
import com.example.model.Agency;
import com.example.dto.ActivationRequestDTO;
import com.example.dto.ActivationResultDTO;
import com.example.enums.ConventionStatus;
import com.example.repository.ConventionRepository;
import com.example.adapter.ETOURServerAdapter;
import java.util.logging.Logger;

/**
 * Controller orchestrating the convention activation workflow.
 * Uses dependency injection for repository and adapter.
 */
public class ConventionActivationController {
    private ConventionRepository conventionRepository;
    private ETOURServerAdapter etourAdapter;
    private Logger logger;
    
    public ConventionActivationController(ConventionRepository conventionRepository, ETOURServerAdapter etourAdapter) {
        this.conventionRepository = conventionRepository;
        this.etourAdapter = etourAdapter;
        this.logger = Logger.getLogger(ConventionActivationController.class.getName());
    }
    
    /**
     * Primary activation method called from the UI layer.
     * 
     * @param activationRequest The activation request DTO
     * @return ActivationResultDTO containing the result
     */
    public ActivationResultDTO activateConvention(ActivationRequestDTO activationRequest) {
        logger.info("Activation request received: " + activationRequest.getRequestId());
        
        // Step 1: Load the convention
        Convention convention = conventionRepository.findById(activationRequest.getRequestId());
        
        if (convention == null) {
            return new ActivationResultDTO(false, "Convention not found", null);
        }
        
        // Step 2: Check if convention is pending
        if (convention.getStatus() != ConventionStatus.PENDING) {
            return new ActivationResultDTO(false, "Convention is not in PENDING state", convention.getId());
        }
        
        // Step 3: Load data for the convention (matching sequence diagram)
        convention.loadData(activationRequest.getRequestId());
        
        // Step 4: Validate convention data
        if (!validateConventionData(convention)) {
            return new ActivationResultDTO(false, "Convention data validation failed", convention.getId());
        }
        
        // Step 5: Initial result (for form display before user confirmation)
        return new ActivationResultDTO(true, "Convention data loaded successfully", convention.getId());
    }
    
    /**
     * Validates the convention data for activation.
     * 
     * @param convention The convention to validate
     * @return true if valid, false otherwise
     */
    boolean validateConventionData(Convention convention) {
        logger.info("Validating convention data for: " + convention.getId());
        
        // Check if convention has required fields
        if (convention.getId() == null || convention.getId().isEmpty()) {
            logger.info("Validation failed: Missing convention ID");
            return false;
        }
        
        if (convention.getName() == null || convention.getName().isEmpty()) {
            logger.info("Validation failed: Missing convention name");
            return false;
        }
        
        // Check refreshment point details (REQ-004, REQ-007)
        if (convention.getRefreshmentPointDetails() == null) {
            logger.info("Validation failed: Missing refreshment point details");
            return false;
        }
        
        if (!convention.getRefreshmentPointDetails().isDesignated()) {
            logger.info("Validation failed: Refreshment point is not designated");
            return false;
        }
        
        logger.info("Convention validation passed for: " + convention.getId());
        return true;
    }
    
    /**
     * Processes the activation after user confirmation.
     * 
     * @param activationRequest The confirmed activation request
     * @return ActivationResultDTO with final result
     */
    public ActivationResultDTO processActivation(ActivationRequestDTO activationRequest) {
        logger.info("Processing activation for: " + activationRequest.getRequestId());
        
        // Load convention again for processing
        Convention convention = conventionRepository.findById(activationRequest.getRequestId());
        
        if (convention == null) {
            return new ActivationResultDTO(false, "Convention not found", null);
        }
        
        // Validate data again
        if (!validateConventionData(convention)) {
            return new ActivationResultDTO(false, "Convention data validation failed", convention.getId());
        }
        
        try {
            // Activate the convention
            convention.activate();
            logger.info("Convention activated: " + convention.getId());
            
            // Save the activated convention
            conventionRepository.save(convention);
            
            // Send notification to ETOUR server
            boolean notificationSent = etourAdapter.sendActivationNotification(convention.getId());
            
            if (notificationSent) {
                processPostActivation(convention);
                return new ActivationResultDTO(true, "Convention activated successfully", convention.getId());
            } else {
                logger.info("ETOUR server notification failed for convention: " + convention.getId());
                return new ActivationResultDTO(false, "Failed to notify ETOUR server", convention.getId());
            }
        } catch (Exception e) {
            logger.info("Error processing activation: " + e.getMessage());
            return new ActivationResultDTO(false, "Activation processing failed: " + e.getMessage(), convention.getId());
        }
    }
    
    /**
     * Internal method to handle post-activation processing.
     * 
     * @param convention The activated convention
     */
    void processPostActivation(Convention convention) {
        // Additional processing logic can be added here
        logger.info("Processing post-activation tasks for: " + convention.getId());
        // Example: Update related entities, trigger notifications, etc.
    }
    
    // Package-private getters for testing
    ConventionRepository getConventionRepository() {
        return conventionRepository;
    }
    
    ETOURServerAdapter getEtourAdapter() {
        return etourAdapter;
    }
}
