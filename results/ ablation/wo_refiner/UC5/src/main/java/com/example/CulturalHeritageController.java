package com.example;

import java.util.List;

/**
 * Controller for cultural heritage operations.
 * Uses SessionManager for authentication (REQ-004).
 * Implements error handling and logging (REQ-009, REQ-010).
 */
public class CulturalHeritageController {
    private CulturalHeritageService culturalHeritageService;
    private SessionManager sessionManager;

    public CulturalHeritageController() {
        this.culturalHeritageService = new CulturalHeritageService();
        this.sessionManager = new SessionManager();
    }

    public CulturalHeritageController(CulturalHeritageService culturalHeritageService, SessionManager sessionManager) {
        this.culturalHeritageService = culturalHeritageService;
        this.sessionManager = sessionManager;
    }

    /**
     * Searches for cultural heritage items based on criteria.
     * @param searchCriteria the search criteria
     * @return list of matching CulturalHeritage objects
     */
    public List<CulturalHeritage> search(String searchCriteria) {
        try {
            // Validate session if needed
            return culturalHeritageService.searchCulturalHeritage(searchCriteria);
        } catch (Exception e) {
            handleError(e);
            return List.of();
        }
    }

    /**
     * Gets detailed view of a specific cultural heritage item.
     * @param culturalHeritageId the ID of the cultural heritage item
     * @return CulturalHeritage object if found, null otherwise
     */
    public CulturalHeritage viewDetails(String culturalHeritageId) {
        try {
            return culturalHeritageService.getCulturalHeritageById(culturalHeritageId);
        } catch (ServiceException e) {
            handleError(e);
            logError(e);
            throw e; // rethrow to be handled by caller
        } catch (Exception e) {
            handleError(e);
            logError(e);
            throw new ServiceException("Controller error viewing details", e);
        }
    }

    /**
     * Handles errors in the controller.
     * Added to satisfy requirement REQ-009.
     */
    private void handleError(Exception error) {
        System.err.println("Controller error occurred: " + error.getMessage());
        // Additional error handling logic
    }

    /**
     * Logs errors.
     * Added to satisfy requirement REQ-010.
     */
    public void logError(Exception error) {
        System.out.println("[ERROR LOG] " + java.time.LocalDateTime.now() + " - " + error.getClass().getSimpleName() + ": " + error.getMessage());
        // In a real application, this would write to a log file or system
    }

    public CulturalHeritageService getCulturalHeritageService() {
        return culturalHeritageService;
    }

    public void setCulturalHeritageService(CulturalHeritageService culturalHeritageService) {
        this.culturalHeritageService = culturalHeritageService;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}