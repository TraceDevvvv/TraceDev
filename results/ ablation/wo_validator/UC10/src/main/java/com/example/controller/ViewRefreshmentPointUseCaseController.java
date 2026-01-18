package com.example.controller;

import com.example.dto.PointOfRestDetailsDTO;
import com.example.model.PointOfRest;
import com.example.repository.PointOfRestRepository;
import com.example.data.DataAccessException;

import java.util.List;

/**
 * Controller for the View Refreshment Point use case.
 * Orchestrates the flow between view and repository.
 */
public class ViewRefreshmentPointUseCaseController {
    private PointOfRestRepository repository;

    public ViewRefreshmentPointUseCaseController(PointOfRestRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves point of rest details by ID, as per sequence diagram.
     * @param pointId the ID of the point of rest
     * @return PointOfRestDetailsDTO containing the details
     * @throws DataAccessException if data retrieval fails
     */
    public PointOfRestDetailsDTO getPointOfRestDetails(String pointId) throws DataAccessException {
        try {
            // Call repository to get domain object
            PointOfRest point = repository.findByID(pointId);
            if (point == null) {
                throw new DataAccessException("Point of rest not found with ID: " + pointId);
            }
            // Create DTO from domain object
            return createPointOfRestDetailsDTO(point);
        } catch (DataAccessException e) {
            // Handle exception as per sequence diagram
            handleException(e);
            throw e; // rethrow after handling
        }
    }

    /**
     * Creates a DTO from the domain model.
     */
    private PointOfRestDetailsDTO createPointOfRestDetailsDTO(PointOfRest domain) {
        // Assumption: fullAddress in DTO corresponds to address in domain
        return new PointOfRestDetailsDTO(
                domain.getId(),
                domain.getName(),
                domain.getAddress(),
                domain.getFacilities()
        );
    }

    /**
     * Handles exceptions (e.g., logging, transforming error messages).
     */
    private void handleException(DataAccessException e) {
        // In a real application, might log the exception or convert to user-friendly message
        System.err.println("Exception in controller: " + e.getMessage());
    }
}