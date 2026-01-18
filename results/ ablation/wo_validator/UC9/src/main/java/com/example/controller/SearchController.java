package com.example.controller;

import com.example.dto.PointOfRestDTO;
import com.example.model.PointOfRest;
import com.example.repository.PointOfRestRepository;
import com.example.validation.SearchValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Use Case Controller that orchestrates the search flow.
 * Implements SearchControllerInterface.
 */
public class SearchController implements SearchControllerInterface {
    private PointOfRestRepository pointOfRestRepository;
    private SearchValidator searchValidator;

    public SearchController(PointOfRestRepository repository, SearchValidator validator) {
        this.pointOfRestRepository = repository;
        this.searchValidator = validator;
    }

    @Override
    public List<PointOfRestDTO> handleSearchRequest(Map<String, Object> criteria) {
        // Validate criteria first
        if (!validateCriteria(criteria)) {
            throw new IllegalArgumentException("Invalid criteria");
        }

        // Execute search
        List<PointOfRest> points = executeSearch(criteria);

        // Convert each PointOfRest to DTO
        List<PointOfRestDTO> dtos = new ArrayList<>();
        for (PointOfRest point : points) {
            dtos.add(PointOfRestDTO.fromPointOfRest(point));
        }

        return dtos;
    }

    /**
     * Validates the search criteria using SearchValidator.
     * @param criteria the criteria map
     * @return true if valid, false otherwise
     */
    private boolean validateCriteria(Map<String, Object> criteria) {
        return searchValidator.validateSearchCriteria(criteria);
    }

    /**
     * Executes the search via repository.
     * @param criteria the criteria map
     * @return list of PointOfRest objects
     */
    private List<PointOfRest> executeSearch(Map<String, Object> criteria) {
        return pointOfRestRepository.findByCriteria(criteria);
    }
}