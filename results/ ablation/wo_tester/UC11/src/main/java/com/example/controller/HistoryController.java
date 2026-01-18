package com.example.controller;

import com.example.dto.PointOfRestDTO;
import com.example.dto.ConventionHistoryDTO;
import com.example.model.PointOfRest;
import com.example.model.ConventionHistory;
import com.example.service.ConventionHistoryService;
import com.example.mapper.ConventionMapper;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Controller that orchestrates the retrieval of convention history.
 * Validates the point of rest designation before proceeding.
 */
public class HistoryController {
    private ConventionHistoryService conventionHistoryService;
    private ConventionMapper conventionMapper;

    public HistoryController(ConventionHistoryService service) {
        this.conventionHistoryService = service;
        this.conventionMapper = new ConventionMapper();
    }

    /**
     * Retrieves convention history for a given point of rest DTO.
     * Includes validation of designated status.
     * Returns a list of DTOs or null if not designated.
     */
    public List<ConventionHistoryDTO> getConventionHistoryForPointOfRest(PointOfRestDTO pointOfRestDTO) {
        // Convert DTO to entity
        PointOfRest pointOfRest = conventionMapper.toEntity(pointOfRestDTO);

        // Validate designated status
        if (!validatePointOfRestDesignated(pointOfRest)) {
            return null;
        }

        try {
            // Delegate to service layer
            List<ConventionHistory> histories = conventionHistoryService.getConventionHistoryForPointOfRest(pointOfRest);
            // Convert entities to DTOs
            return conventionMapper.toDTOList(histories);
        } catch (ConnectionException e) {
            // Rethrow to be handled by UI
            throw e;
        }
    }

    /**
     * Validates that the point of rest is designated.
     * Added to satisfy Entry Conditions.
     */
    public boolean validatePointOfRestDesignated(PointOfRest pointOfRest) {
        return pointOfRest.isDesignated();
    }
}