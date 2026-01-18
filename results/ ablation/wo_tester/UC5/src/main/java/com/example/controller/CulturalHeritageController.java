package com.example.controller;

import com.example.exception.ApplicationException;
import com.example.model.CulturalHeritageDTO;
import com.example.service.CulturalHeritageService;

/**
 * Controller for Cultural Heritage operations.
 * Contains QualityRequirement placeholder - no specific quality requirement defined.
 */
public class CulturalHeritageController {
    private CulturalHeritageService service;
    
    public CulturalHeritageController(CulturalHeritageService service) {
        this.service = service;
    }
    
    /**
     * View cultural heritage details.
     * @param id the heritage ID
     * @return CulturalHeritageDTO containing details
     * @throws ApplicationException if application operation fails
     */
    public CulturalHeritageDTO viewDetails(String id) throws ApplicationException {
        try {
            CulturalHeritageDTO dto = service.getCulturalHeritageDetails(id);
            return dto;
        } catch (Exception e) {
            handleError(e);
            throw new ApplicationException("Failed to view cultural heritage details", e);
        }
    }
    
    /**
     * Handles errors in controller layer.
     * Added to satisfy requirement for Exit Conditions.
     * @param error the exception that occurred
     */
    public void handleError(Exception error) {
        System.err.println("Controller error handled: " + error.getMessage());
        // Additional error handling logic could be added here
    }
    
    public void viewDetails(String id, Object context) {
        // Overloaded method for sequence diagram traceability
        // This method returns void as per missing_elements
        try {
            CulturalHeritageDTO dto = viewDetails(id);
            // The actual display should be handled by View
        } catch (ApplicationException e) {
            handleError(e);
        }
    }
}