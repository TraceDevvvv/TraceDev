package com.example.controller;

import com.example.dto.RefreshmentPointDTO;
import com.example.dto.SearchCriteriaDTO;
import com.example.service.RefreshmentPointSearchService;

import java.util.List;

/**
 * Controller for refreshment point search functionality.
 * Interacts with User actor and coordinates search flow.
 */
public class RefreshmentPointSearchController {
    private RefreshmentPointSearchService service;

    // Constructor with dependency injection
    public RefreshmentPointSearchController(RefreshmentPointSearchService service) {
        this.service = service;
    }

    /**
     * Activates search functionality (REQ-ACT-001).
     * Called by User actor.
     */
    public void activateSearchFunctionality() {
        System.out.println("Search functionality activated.");
    }

    /**
     * Shows the search form.
     * Called after activation.
     */
    public void showSearchForm() {
        System.out.println("Displaying search form...");
    }

    /**
     * Handles submission of the search form.
     * Implements main and alternative flows from sequence diagram.
     * @param searchCriteriaDTO criteria from user input
     * @return list of refreshment point DTOs to display
     */
    public List<RefreshmentPointDTO> handleSearchFormSubmission(SearchCriteriaDTO searchCriteriaDTO) {
        // Validate criteria
        if (!validateCriteria(searchCriteriaDTO)) {
            // Invalid criteria: return empty list (alternative flow)
            System.out.println("Criteria invalid, returning empty list.");
            return new java.util.ArrayList<>();
        }

        // Map DTO to domain criteria
        com.example.domain.SearchCriteria criteria = service.mapToDomainCriteria(searchCriteriaDTO);

        try {
            // Call service to search
            List<com.example.domain.RefreshmentPoint> domainList = service.searchRefreshmentPoints(criteria);

            // Convert domain objects to DTOs
            List<RefreshmentPointDTO> result = service.convertToDTOs(domainList);

            // Display results (in real scenario, this would be returned to view)
            return result;
        } catch (com.example.exception.ServiceException e) {
            // Alternative flow: connection interrupted
            displayErrorMessage("Connection to server interrupted");
            // Return empty list or re-throw a runtime exception depending on requirement.
            // For simplicity, return empty list.
            return new java.util.ArrayList<>();
        }
    }

    /**
     * Validates the criteria DTO.
     * @param dto the criteria DTO to validate
     * @return true if valid, false otherwise
     */
    public boolean validateCriteria(SearchCriteriaDTO dto) {
        // Simple validation: locationFilter not empty and maxDistance non-negative
        if (dto == null) {
            return false;
        }
        boolean locationValid = dto.getLocationFilter() != null && !dto.getLocationFilter().trim().isEmpty();
        boolean distanceValid = dto.getMaxDistance() >= 0;
        return locationValid && distanceValid;
    }

    /**
     * Displays an error message to the user.
     * Used in alternative flow when server connection is lost.
     * @param message the error message
     */
    public void displayErrorMessage(String message) {
        System.err.println("ERROR: " + message);
    }
}