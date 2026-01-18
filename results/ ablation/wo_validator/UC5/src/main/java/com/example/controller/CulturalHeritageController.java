package com.example.controller;

import com.example.service.CulturalHeritageService;
import com.example.service.AuthenticationService;
import com.example.dto.CulturalHeritageDetailsDTO;
import com.example.dto.CulturalHeritageSearchDTO;
import com.example.model.User;
import java.util.List;

/**
 * Controller handling requests related to cultural heritage.
 * Coordinates between the view layer and the service layer.
 */
public class CulturalHeritageController {

    private CulturalHeritageService heritageService;
    private AuthenticationService authenticationService;

    /**
     * Constructor for dependency injection.
     * @param heritageService the service for cultural heritage operations
     * @param authenticationService the service for authentication and authorization
     */
    public CulturalHeritageController(CulturalHeritageService heritageService, AuthenticationService authenticationService) {
        this.heritageService = heritageService;
        this.authenticationService = authenticationService;
    }

    /**
     * Retrieves details of a specific cultural heritage by its ID.
     * Checks if the current user has the AGENCY_OPERATOR role.
     * @param id the unique identifier of the cultural heritage
     * @return CulturalHeritageDetailsDTO containing detailed information
     */
    public CulturalHeritageDetailsDTO viewHeritageDetails(String id) {
        // Check authentication and authorization
        User currentUser = authenticationService.getCurrentUser();
        boolean isAuthorized = authenticationService.isUserInRole(currentUser, "AGENCY_OPERATOR");
        if (!isAuthorized) {
            throw new SecurityException("Access Denied: User is not authorized.");
        }
        // Retrieve details from service layer
        return heritageService.getHeritageDetails(id);
    }

    /**
     * Searches for cultural heritage items based on predefined criteria.
     * Checks if the current user has the AGENCY_OPERATOR role.
     * @return List of CulturalHeritageSearchDTO containing search results
     */
    public List<CulturalHeritageSearchDTO> searchHeritage() {
        // Check authentication and authorization
        User currentUser = authenticationService.getCurrentUser();
        boolean isAuthorized = authenticationService.isUserInRole(currentUser, "AGENCY_OPERATOR");
        if (!isAuthorized) {
            throw new SecurityException("Access Denied: User is not authorized.");
        }
        // For simplicity, we create a default criteria; in a real scenario, criteria might be passed as parameter.
        com.example.model.HeritageSearchCriteria criteria = new com.example.model.HeritageSearchCriteria();
        // You could set some default or UI-provided values here.
        return heritageService.searchHeritage(criteria);
    }
}