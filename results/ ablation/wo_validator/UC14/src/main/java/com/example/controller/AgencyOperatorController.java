package com.example.controller;

import com.example.dto.TouristAccountSearchCriteriaDTO;
import com.example.dto.TouristAccountDTO;
import com.example.handler.TouristAccountSearchQueryHandler;
import java.util.List;

/**
 * Controller for agency operator operations.
 * Handles requests related to tourist account search.
 */
public class AgencyOperatorController {
    private TouristAccountSearchQueryHandler queryHandler;

    public AgencyOperatorController(TouristAccountSearchQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    /**
     * Searches tourist accounts based on provided criteria.
     * Implements the main success flow from the sequence diagram.
     * Also handles connection interruption as per the alt block.
     * @param searchCriteria the search criteria DTO
     * @return list of tourist account DTOs
     */
    public List<TouristAccountDTO> searchTouristAccounts(TouristAccountSearchCriteriaDTO searchCriteria) {
        try {
            // Quality requirement: search must complete within 3 seconds.
            // This is a non-functional requirement; we assume the underlying layers comply.
            // For demonstration, we just pass the request to the query handler.
            return queryHandler.handle(searchCriteria);
        } catch (RuntimeException e) {
            // Catch connection exceptions (simulated by RuntimeException in repository).
            // As per sequence diagram alt block, return an error message.
            // In a real scenario, we would have a dedicated exception type.
            if (e.getCause() instanceof java.sql.SQLException) {
                throw new RuntimeException("Connection to server interrupted");
            }
            throw e;
        }
    }
}