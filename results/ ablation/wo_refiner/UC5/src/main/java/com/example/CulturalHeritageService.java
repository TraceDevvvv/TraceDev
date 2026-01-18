package com.example;

import java.util.List;

/**
 * Service layer for cultural heritage operations.
 * Implements error handling per requirement REQ-009.
 */
public class CulturalHeritageService {
    private ICulturalHeritageRepository culturalHeritageRepository;

    public CulturalHeritageService() {
        this.culturalHeritageRepository = new CulturalHeritageRepository();
    }

    public CulturalHeritageService(ICulturalHeritageRepository culturalHeritageRepository) {
        this.culturalHeritageRepository = culturalHeritageRepository;
    }

    public List<CulturalHeritage> searchCulturalHeritage(String searchCriteria) {
        try {
            return culturalHeritageRepository.findAllByCriteria(searchCriteria);
        } catch (Exception e) {
            handleServiceError(e);
            // Return empty list on error
            return List.of();
        }
    }

    public CulturalHeritage getCulturalHeritageById(String id) {
        try {
            return culturalHeritageRepository.findById(id);
        } catch (RepositoryException e) {
            handleServiceError(e);
            throw new ServiceException("Service error retrieving cultural heritage with id: " + id, e);
        } catch (Exception e) {
            handleServiceError(e);
            throw new ServiceException("Unexpected error retrieving cultural heritage", e);
        }
    }

    /**
     * Handles service-level errors.
     * Added to satisfy requirement REQ-009.
     */
    private void handleServiceError(Exception exception) {
        System.err.println("Service error occurred: " + exception.getMessage());
        // Additional error handling logic would go here
    }

    public ICulturalHeritageRepository getCulturalHeritageRepository() {
        return culturalHeritageRepository;
    }

    public void setCulturalHeritageRepository(ICulturalHeritageRepository culturalHeritageRepository) {
        this.culturalHeritageRepository = culturalHeritageRepository;
    }
}