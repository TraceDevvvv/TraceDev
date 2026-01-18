
package com.example.controller;

import com.example.model.*;
import com.example.repository.SiteRepository;
import com.example.service.LocationService;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Controller that handles the advanced search use case.
 * Coordinates between form, location service, and repository.
 */
public class SiteSearchController {
    private SiteRepository siteRepository;
    private LocationService locationService;
    private long processStartTime;

    public SiteSearchController(SiteRepository siteRepository, LocationService locationService) {
        this.siteRepository = siteRepository;
        this.locationService = locationService;
    }

    /**
     * Handles an advanced search request.
     * Implements the flow described in the sequence diagram.
     */
    public List<Site> handleAdvancedSearch(SearchRequest request) {
        processStartTime = System.currentTimeMillis();

        // Get location from tourist (simplified: directly from locationService)
        Location touristLocation = null;
        try {
            touristLocation = locationService.getCurrentLocation();
        } catch (Exception e) {
            // Location unavailable: display error (tourist reference not directly available here)
            // Assumption: we throw a runtime exception to be caught by caller
            throw new RuntimeException("Location unavailable");
        }

        request.setTouristLocation(touristLocation);

        SearchCriteria criteria = request.getSearchCriteria();
        Location location = request.getTouristLocation();

        List<Site> sites;
        try {
            sites = siteRepository.findByCriteria(criteria, location);
        } catch (ConnectionException e) {
            handleConnectionError(e);
            // Return an empty list as per sequence diagram note
            sites = java.util.Collections.emptyList();
        }

        checkProcessTime();
        return sites;
    }

    /**
     * Handles a connection error as per class diagram.
     */
    public void handleConnectionError(ConnectionException exception) {
        System.err.println("Connection error handled: " + exception.getMessage());
        // Additional error handling logic could go here
    }

    /**
     * Checks if processing time exceeds 15 seconds (as noted in class diagram).
     */
    private void checkProcessTime() {
        long duration = System.currentTimeMillis() - processStartTime;
        if (duration > 15000) {
            System.out.println("Warning: Process time > 15s (" + duration + "ms)");
        }
    }

    public void displayResults(List<Site> sites) {
        // This would normally update a view, but for completion, we'll print
        System.out.println("Controller: Displaying results with " + sites.size() + " sites");
    }
}
