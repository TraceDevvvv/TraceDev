package com.example.handler;

import com.example.dto.TouristAccountSearchCriteriaDTO;
import com.example.dto.TouristAccountDTO;
import java.util.List;

/**
 * Query handler interface for searching tourist accounts.
 */
public interface TouristAccountSearchQueryHandler {
    /**
     * Handles the search query and returns a list of tourist account DTOs.
     * @param searchCriteria the search criteria
     * @return list of tourist account DTOs
     */
    List<TouristAccountDTO> handle(TouristAccountSearchCriteriaDTO searchCriteria);
}