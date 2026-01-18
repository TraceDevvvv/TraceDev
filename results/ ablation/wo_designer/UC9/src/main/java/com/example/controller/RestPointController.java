package com.example.controller;

import com.example.dto.RestPoint;
import com.example.dto.RestPointSearchCriteria;
import com.example.service.RestPointService;
import com.example.exception.ServerConnectionException;
import java.util.List;

/**
 * Controller for handling rest point search requests.
 */
public class RestPointController {

    private final RestPointService restPointService = new RestPointService();

    /**
     * Handles the search request from the user.
     * @param criteria the search criteria from the user
     * @return list of matching rest points
     */
    public List<RestPoint> handleSearchRequest(RestPointSearchCriteria criteria) {
        try {
            // Validate user authentication (simulated)
            if (!isUserAuthenticated()) {
                throw new SecurityException("User is not authenticated.");
            }

            // Process the search
            return restPointService.searchRestPoints(criteria);

        } catch (ServerConnectionException e) {
            System.err.println("Server connection error: " + e.getMessage());
            return List.of(); // Return empty list on connection error
        } catch (Exception e) {
            System.err.println("Error processing search: " + e.getMessage());
            return List.of();
        }
    }

    private boolean isUserAuthenticated() {
        // Simulated authentication check
        // In a real application, this would validate a token or session
        return true; // Assume authenticated for this example
    }
}