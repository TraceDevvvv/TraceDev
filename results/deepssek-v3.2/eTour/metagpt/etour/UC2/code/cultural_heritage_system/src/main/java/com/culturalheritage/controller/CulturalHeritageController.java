package com.culturalheritage.controller;

import com.culturalheritage.model.CulturalHeritage;
import com.culturalheritage.service.CulturalHeritageService;
import com.culturalheritage.exception.ValidationException;
import com.culturalheritage.exception.DuplicateHeritageException;

/**
 * CulturalHeritageController handles the business logic for the InsertCulturalHeritage use case.
 * This controller orchestrates the flow of inserting a new cultural heritage object,
 * coordinating between the UI, service layer, and repository layer.
 * It follows the MVC pattern as specified in the system design.
 */
public class CulturalHeritageController {
    
    private final CulturalHeritageService service;
    
    /**
     * Constructs a CulturalHeritageController with the specified service.
     * 
     * @param service The CulturalHeritageService to use for business logic operations
     * @throws IllegalArgumentException if service is null
     */
    public CulturalHeritageController(CulturalHeritageService service) {
        if (service == null) {
            throw new IllegalArgumentException("CulturalHeritageService cannot be null");
        }
        this.service = service;
    }
    
    /**
     * Handles the complete insertion form process for a new cultural heritage.
     * This method orchestrates the flow from displaying the form to final insertion.
     * 
     * @return The inserted CulturalHeritage object, or null if insertion was cancelled
     */
    public CulturalHeritage handleInsertionForm() {
        System.out.println("CulturalHeritageController: Starting insertion form process...");
        
        // Step 1: Display form (in a real system, this would return form data structure)
        CulturalHeritage formData = displayForm();
        
        if (formData == null) {
            System.out.println("CulturalHeritageController: Form data is null, insertion cancelled.");
            return null;
        }
        
        try {
            // Step 2: Insert the cultural heritage using the service layer
            CulturalHeritage insertedHeritage = insertCulturalHeritage(formData);
            
            // Step 3: Notify successful inclusion
            notifyInclusion(insertedHeritage);
            
            return insertedHeritage;
        } catch (ValidationException e) {
            System.err.println("CulturalHeritageController: Validation error - " + e.getMessage());
            throw e; // Re-throw to be handled by UI
        } catch (DuplicateHeritageException e) {
            System.err.println("CulturalHeritageController: Duplicate detected - " + e.getMessage());
            throw e; // Re-throw to be handled by UI
        } catch (Exception e) {
            System.err.println("CulturalHeritageController: Unexpected error during insertion - " + e.getMessage());
            throw new RuntimeException("Failed to insert cultural heritage", e);
        }
    }
    
    /**
     * Inserts a new cultural heritage object into the system.
     * This method coordinates the validation, duplicate checking, and persistence.
     * 
     * @param heritage The CulturalHeritage object to insert
     * @return The inserted CulturalHeritage object with generated ID
     * @throws ValidationException if data validation fails
     * @throws DuplicateHeritageException if a duplicate cultural heritage is detected
     */
    public CulturalHeritage insertCulturalHeritage(CulturalHeritage heritage) {
        if (heritage == null) {
            throw new IllegalArgumentException("CulturalHeritage cannot be null");
        }
        
        System.out.println("CulturalHeritageController: Inserting cultural heritage - " + heritage.getName());
        
        // Step 4 from use case: Verify the data entered
        boolean isDataValid = verifyData(heritage);
        
        if (!isDataValid) {
            throw new ValidationException("Data verification failed for cultural heritage: " + heritage.getName());
        }
        
        // Delegate to service layer for actual insertion
        CulturalHeritage insertedHeritage = service.insertCulturalHeritage(heritage);
        
        // Step 6 from use case: Memorize the new cultural good
        memorizeCulturalHeritage(insertedHeritage);
        
        return insertedHeritage;
    }
    
    /**
     * Displays the form for entering cultural heritage data.
     * In this console-based implementation, this method creates a placeholder object.
     * In a real GUI application, this would return a form structure or template.
     * 
     * @return A new CulturalHeritage object with default values (to be filled by UI)
     */
    public CulturalHeritage displayForm() {
        System.out.println("CulturalHeritageController: Displaying cultural heritage form...");
        
        // Create a new CulturalHeritage object with default/empty values
        // The actual form filling will be done by the ConsoleUI
        CulturalHeritage formData = new CulturalHeritage();
        
        // Set default agency code (in a real system, this would come from logged-in agency)
        formData.setAgencyCode("AGENCY-001");
        
        return formData;
    }
    
    /**
     * Verifies the data entered in the cultural heritage form.
     * This method performs basic validation before delegating to the service layer.
     * 
     * @param heritage The CulturalHeritage object to verify
     * @return true if data is valid, false otherwise
     */
    public boolean verifyData(CulturalHeritage heritage) {
        if (heritage == null) {
            System.err.println("CulturalHeritageController: Cannot verify null heritage object");
            return false;
        }
        
        System.out.println("CulturalHeritageController: Verifying data for - " + heritage.getName());
        
        // Check for null or empty required fields
        if (heritage.getName() == null || heritage.getName().trim().isEmpty()) {
            System.err.println("CulturalHeritageController: Name is required");
            return false;
        }
        
        if (heritage.getType() == null || heritage.getType().trim().isEmpty()) {
            System.err.println("CulturalHeritageController: Type is required");
            return false;
        }
        
        if (heritage.getLocation() == null || heritage.getLocation().trim().isEmpty()) {
            System.err.println("CulturalHeritageController: Location is required");
            return false;
        }
        
        if (heritage.getAgencyCode() == null || heritage.getAgencyCode().trim().isEmpty()) {
            System.err.println("CulturalHeritageController: Agency code is required");
            return false;
        }
        
        // Additional validation can be added here based on business rules
        System.out.println("CulturalHeritageController: Data verification passed for - " + heritage.getName());
        return true;
    }
    
    /**
     * Memorizes (stores/registers) the new cultural heritage in the system.
     * This method represents the final step of persisting the cultural heritage.
     * 
     * @param heritage The CulturalHeritage object to memorize
     * @return The memorized CulturalHeritage object
     */
    public CulturalHeritage memorizeCulturalHeritage(CulturalHeritage heritage) {
        if (heritage == null) {
            throw new IllegalArgumentException("CulturalHeritage cannot be null for memorization");
        }
        
        System.out.println("CulturalHeritageController: Memorizing cultural heritage - " + heritage.getName());
        
        // In this implementation, memorization is handled by the service layer during insertion
        // This method serves as a placeholder for any additional memorization logic
        // For example, logging, auditing, or notification to other systems
        
        // Log the successful memorization
        System.out.println("CulturalHeritageController: Successfully memorized cultural heritage with ID: " + heritage.getId());
        
        return heritage;
    }
    
    /**
     * Notifies about the successful inclusion of a cultural heritage.
     * This method handles the exit condition: "The system shall notify the proper inclusion of the cultural."
     * 
     * @param heritage The CulturalHeritage object that was successfully included
     */
    public void notifyInclusion(CulturalHeritage heritage) {
        if (heritage == null) {
            System.err.println("CulturalHeritageController: Cannot notify inclusion of null heritage");
            return;
        }
        
        System.out.println("CulturalHeritageController: Notifying successful inclusion...");
        
        // Display notification message
        System.out.println("\n=== CULTURAL HERITAGE SUCCESSFULLY REGISTERED ===");
        System.out.println("ID: " + heritage.getId());
        System.out.println("Name: " + heritage.getName());
        System.out.println("Type: " + heritage.getType());
        System.out.println("Location: " + heritage.getLocation());
        System.out.println("Era: " + heritage.getEra());
        System.out.println("Agency Code: " + heritage.getAgencyCode());
        System.out.println("Registration Date: " + heritage.getRegistrationDate());
        System.out.println("================================================\n");
        
        // In a real system, this could also:
        // 1. Send email notifications
        // 2. Update dashboards
        // 3. Trigger workflow processes
        // 4. Log to audit system
    }
    
    /**
     * Gets the CulturalHeritageService used by this controller.
     * 
     * @return The CulturalHeritageService instance
     */
    public CulturalHeritageService getService() {
        return service;
    }
}