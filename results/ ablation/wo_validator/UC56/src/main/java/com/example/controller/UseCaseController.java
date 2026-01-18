package com.example.controller;

import com.example.dto.PositionDTO;
import com.example.dto.SearchDTO;
import com.example.service.SystemInitializationService;

/**
 * Controller handling the system initialization use case.
 */
public class UseCaseController {
    private SystemInitializationService initializationService;

    /**
     * Constructor with dependency injection.
     * @param initializationService the service used to initialize the system
     */
    public UseCaseController(SystemInitializationService initializationService) {
        this.initializationService = initializationService;
    }

    /**
     * Handles the system initialization request.
     * Implements the main flow from the sequence diagram.
     * @param searchData the search data provided by the tourist
     */
    public void handleInitializeSystem(SearchDTO searchData) {
        // Delegate to service
        PositionDTO position = initializationService.initializeSystem(searchData);
        // Return result to caller (Tourist). In a real app, this might be via a callback or response object.
        // For simplicity, we print to console.
        if (position != null) {
            System.out.println("Position retrieved successfully:");
            System.out.println("Latitude: " + position.getLatitude());
            System.out.println("Longitude: " + position.getLongitude());
            System.out.println("Accuracy: " + position.getAccuracy());
            System.out.println("Timestamp: " + position.getTimestamp());
            // In the diagram, controller returns positionDTO to Tourist.
            // Here we simulate that by just having the method complete.
        } else {
            System.out.println("Error: Position not detectable.");
        }
    }
}