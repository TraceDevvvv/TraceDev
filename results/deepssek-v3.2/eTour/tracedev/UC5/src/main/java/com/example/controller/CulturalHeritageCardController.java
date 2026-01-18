package com.example.controller;

import com.example.dto.CulturalGoodDTO;
import com.example.exception.ServiceException;
import com.example.model.CulturalGood;
import com.example.service.CulturalGoodService;

/**
 * Controller responsible for handling requests to view cultural heritage card details.
 * Implements the flow described in the sequence diagram.
 */
public class CulturalHeritageCardController {
    private CulturalGoodService culturalGoodService;

    public CulturalHeritageCardController(CulturalGoodService culturalGoodService) {
        this.culturalGoodService = culturalGoodService;
    }

    /**
     * Retrieves a cultural good by ID and converts it to a DTO for display.
     * Corresponds to step 2 in the sequence diagram.
     * @param culturalGoodId the ID of the cultural good
     * @return the CulturalGoodDTO containing details
     * @throws ServiceException if an error occurs during retrieval
     */
    public CulturalGoodDTO viewCard(String culturalGoodId) throws ServiceException {
        try {
            // Step 3 in sequence diagram: call service to get cultural good
            CulturalGood culturalGood = culturalGoodService.getCulturalGoodById(culturalGoodId);
            // Step 7: create DTO from the entity
            CulturalGoodDTO dto = new CulturalGoodDTO(culturalGood);
            return dto;
        } catch (ServiceException e) {
            // Handle error as per class diagram method
            handleError(e);
            throw e; // rethrow to let UI know about the error
        }
    }

    /**
     * Handles ServiceException as per class diagram.
     * In this implementation, it logs the error.
     * Corresponds to the error handling in the sequence diagram.
     * @param error the ServiceException to handle
     */
    public void handleError(ServiceException error) {
        // Log the error; in a real system, this might involve additional actions.
        System.err.println("Error in CulturalHeritageCardController: " + error.getMessage());
        if (error.getCause() != null) {
            System.err.println("Caused by: " + error.getCause().getMessage());
        }
    }

    // Additional method to correspond with sequence diagram message "Creates CulturalGoodDTO"
    // This is already done in viewCard method; but we ensure it's explicit.
    public CulturalGoodDTO createCulturalGoodDTO(CulturalGood culturalGood) {
        return new CulturalGoodDTO(culturalGood);
    }
}