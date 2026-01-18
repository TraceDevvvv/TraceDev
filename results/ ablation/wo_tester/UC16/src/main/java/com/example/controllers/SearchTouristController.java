package com.example.controllers;

import com.example.domain.Tourist;
import com.example.repository.TouristRepository;
import java.util.List;

/**
 * Controller responsible for tourist search operations.
 */
public class SearchTouristController {
    private TouristRepository touristRepository;

    public SearchTouristController(TouristRepository repo) {
        this.touristRepository = repo;
    }

    /**
     * Searches for tourists based on criteria.
     * @param criteria search criteria
     * @return list of matched tourists
     */
    public List<Tourist> searchTourist(String criteria) {
        return touristRepository.findAll(criteria);
    }

    /**
     * Finds a specific tourist by ID.
     * This method is used in the sequence diagram when an operator selects a tourist.
     * Assumption: Added to fulfill the sequence diagram.
     * @param touristId the tourist identifier
     * @return Tourist object or null if not found
     */
    public Tourist findTouristById(String touristId) {
        return touristRepository.findById(touristId);
    }

    /**
     * This method corresponds to sequence message "Query tourists" (m3).
     * The sequence diagram shows TR (TouristRepository) receives "Query tourists" from STC.
     * This method delegates to repository's findAll.
     */
    public List<Tourist> queryTourists(String criteria) {
        return touristRepository.findAll(criteria);
    }

    /**
     * This method corresponds to sequence message "SELECT tourist by ID" (m11).
     * It calls the repository's findById, which sends "SELECT tourist by ID" to DB.
     */
    public Tourist selectTouristById(String touristId) {
        return touristRepository.findById(touristId);
    }
}