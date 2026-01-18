package com.example.controller;

import com.example.dto.SearchRequest;
import com.example.dto.SearchResponse;
import com.example.dto.SiteDTO;
import com.example.domain.Site;
import com.example.repository.SiteRepository;
import com.example.repository.RepositoryException;
import com.example.service.AuthenticationService;
import com.example.service.SystemStatus;
import java.util.List;
import java.util.ArrayList;

/**
 * Controller for searching sites.
 * Implements the use case FR-UC1.
 * Quality Requirement: Process must complete within time limit (timeLimit = X ms)
 */
public class SearchSiteController {
    private SiteRepository siteRepository;
    private AuthenticationService authService;
    private SystemStatus systemStatus;

    /**
     * Constructs a SearchSiteController with the required serv.
     * @param siteRepository the site repository
     * @param authService the authentication service
     * @param systemStatus the system status service
     */
    public SearchSiteController(SiteRepository siteRepository, AuthenticationService authService, SystemStatus systemStatus) {
        this.siteRepository = siteRepository;
        this.authService = authService;
        this.systemStatus = systemStatus;
    }

    /**
     * Searches for sites based on the request.
     * This method implements the main flow of the use case.
     * @param request the search request
     * @return the search response
     */
    public SearchResponse searchSite(SearchRequest request) {
        // Entry Conditions Check
        if (!isEntryConditionsMet()) {
            return new SearchResponse(null, false, "Entry conditions not met: user not authenticated or system not running.");
        }

        // Quality Requirement: We should measure time and ensure it is within limit.
        long startTime = System.currentTimeMillis();

        try {
            // Perform the search
            List<Site> sites = siteRepository.findByCriteria(request.getSearchCriteria());

            // Convert domain objects to DTOs
            List<SiteDTO> siteDTOs = convertToDTOs(sites);

            // Create a successful response
            SearchResponse response = new SearchResponse(siteDTOs, true, null);

            // Check time limit (assuming timeLimit = 5000 ms for example)
            long timeLimit = 5000;
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed > timeLimit) {
                // Log warning if time limit exceeded (not part of the diagrams, but good practice)
                System.err.println("Warning: Search process took " + elapsed + " ms, exceeding limit of " + timeLimit + " ms.");
            }

            return response;
        } catch (RepositoryException e) {
            // Handle repository exception (e.g., connection error)
            return new SearchResponse(null, false, e.getMessage());
        } catch (Exception e) {
            // Handle any other unexpected errors
            return new SearchResponse(null, false, "An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Converts a list of Site domain objects to a list of SiteDTO.
     * @param sites the list of sites
     * @return the list of site DTOs
     */
    public List<SiteDTO> convertToDTOs(List<Site> sites) {
        List<SiteDTO> dtos = new ArrayList<>();
        for (Site site : sites) {
            dtos.add(new SiteDTO(site));
        }
        return dtos;
    }

    /**
     * Checks the entry conditions: user authentication and system status.
     * @return true if both conditions are met, false otherwise
     */
    private boolean isEntryConditionsMet() {
        // For simplicity, we assume current user is "currentUser".
        String user = "currentUser";
        boolean authenticated = authService.isAuthenticated(user);
        boolean running = systemStatus.isRunning();
        return authenticated && running;
    }
}