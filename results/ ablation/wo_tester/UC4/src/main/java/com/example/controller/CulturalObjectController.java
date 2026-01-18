package com.example.controller;

import com.example.authentication.AuthenticationService;
import com.example.authentication.AuthenticationServiceImpl;
import com.example.dto.CulturalObjectDTO;
import com.example.dto.SearchFormDTO;
import com.example.etour.ETOURService;
import com.example.etour.ETOURServiceImpl;
import com.example.service.CulturalObjectService;
import com.example.service.CulturalObjectServiceImpl;
import com.example.system.SystemStatusService;
import com.example.system.SystemStatusServiceImpl;
import java.util.List;
import java.util.Map;

/**
 * Controller for cultural object search operations.
 * Orchestrates the search flow as per the sequence diagram.
 */
public class CulturalObjectController {
    private CulturalObjectService culturalObjectService;
    private AuthenticationService authenticationService;
    private SystemStatusService systemStatusService;
    private ETOURService etourService;

    public CulturalObjectController() {
        this.culturalObjectService = new CulturalObjectServiceImpl();
        this.authenticationService = new AuthenticationServiceImpl();
        this.systemStatusService = new SystemStatusServiceImpl();
        this.etourService = new ETOURServiceImpl();
    }

    /**
     * Checks if search functionality is accessible.
     * Entry Condition: Search functionality IS accessible.
     */
    public static boolean isSearchAccessible() {
        // In a real app, this might check feature toggles, user permissions, etc.
        return true;
    }

    /**
     * Activates the search process (Flow of Events 1).
     * Performs system and authentication checks.
     */
    public void activateSearch() {
        // Check system availability
        if (!systemStatusService.checkSystemAvailability()) {
            System.out.println("System unavailable");
            return;
        }

        // Authenticate user
        if (!authenticationService.authenticateUser()) {
            System.out.println("Authentication required");
            return;
        }

        // Proceed to get search form (Flow of Events 2 will be triggered by the UI)
        System.out.println("Search activated. Ready for criteria input.");
    }

    /**
     * Returns a new SearchFormDTO for the UI (Flow of Events 2).
     */
    public SearchFormDTO getSearchForm() {
        return new SearchFormDTO();
    }

    /**
     * Fills and submits the search form (Flow of Events 3-4).
     * @param formData Map containing form inputs.
     */
    public void fillAndSubmitSearch(Map<String, Object> formData) {
        // Create DTO from form data
        SearchFormDTO searchForm = new SearchFormDTO(formData);
        // Process the search
        List<CulturalObjectDTO> results = processSearch(searchForm);
        // Display results
        displayResults(results);
    }

    /**
     * Processes the search by delegating to the service layer (Flow of Events 4-5).
     */
    public List<CulturalObjectDTO> processSearch(SearchFormDTO searchForm) {
        try {
            List<CulturalObjectDTO> dtos = culturalObjectService.searchObjects(searchForm);
            prepareResponse(dtos);
            return dtos;
        } catch (IllegalArgumentException e) {
            // Validation error
            System.out.println("Validation error: " + e.getMessage());
            displayErrorMessage(e.getMessage());
        } catch (RuntimeException e) {
            // ETOUR connection error or other runtime exception
            System.out.println("Error: " + e.getMessage());
            displayErrorMessage(e.getMessage());
        }
        return List.of(); // empty list on error
    }

    /**
     * Prepares the response for the UI (Flow of Events 6).
     */
    public void prepareResponse(List<CulturalObjectDTO> dtos) {
        // Could add logging, transformation, or other response preparation.
        System.out.println("Prepared response with " + dtos.size() + " objects.");
    }

    /**
     * Displays the search results (Flow of Events 6).
     */
    public void displayResults(List<CulturalObjectDTO> dtos) {
        if (dtos.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("Displaying " + dtos.size() + " cultural objects:");
            for (CulturalObjectDTO dto : dtos) {
                System.out.println(" - " + dto.getTitle() + " (" + dto.getCulturalPeriod() + ")");
            }
        }
    }

    private void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}