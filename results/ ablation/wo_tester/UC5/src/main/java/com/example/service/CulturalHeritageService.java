package com.example.service;

import com.example.exception.ServiceException;
import com.example.model.CulturalHeritage;
import com.example.model.CulturalHeritageDTO;
import com.example.repository.ICulturalHeritageRepository;

/**
 * Service layer for Cultural Heritage operations.
 */
public class CulturalHeritageService {
    private ICulturalHeritageRepository repository;
    
    public CulturalHeritageService(ICulturalHeritageRepository repository) {
        this.repository = repository;
    }
    
    /**
     * Gets cultural heritage details by ID.
     * @param id the heritage ID
     * @return CulturalHeritageDTO containing details
     * @throws ServiceException if service operation fails
     */
    public CulturalHeritageDTO getCulturalHeritageDetails(String id) throws ServiceException {
        try {
            CulturalHeritage heritage = repository.findById(id);
            
            if (heritage == null) {
                throw new ServiceException("Cultural heritage not found with ID: " + id);
            }
            
            // Convert domain object to DTO - sequence diagram m17
            CulturalHeritageDTO dto = convertToDTO(heritage);
            return dto;
            
        } catch (Exception e) {
            handleError(e);
            throw new ServiceException("Failed to get cultural heritage details", e);
        }
    }
    
    /**
     * Handles errors in service layer.
     * Added to satisfy requirement for Exit Conditions.
     * @param error the exception that occurred
     */
    public void handleError(Exception error) {
        System.err.println("Service error handled: " + error.getMessage());
        // Additional error handling logic could be added here
    }
    
    /**
     * Convert to DTO - sequence diagram m17
     */
    private CulturalHeritageDTO convertToDTO(CulturalHeritage heritage) {
        return heritage.toDTO();
    }
}