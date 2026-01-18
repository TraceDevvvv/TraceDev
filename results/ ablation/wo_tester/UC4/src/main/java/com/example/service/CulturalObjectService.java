package com.example.service;

import com.example.dto.CulturalObjectDTO;
import com.example.dto.SearchFormDTO;
import java.util.List;

/**
 * Service interface for cultural object business logic.
 */
public interface CulturalObjectService {
    /**
     * Searches for cultural objects based on the given criteria.
     * Quality Requirement: Must complete within 2 seconds.
     * @param searchCriteria The search criteria encapsulated in a DTO.
     * @return A list of CulturalObjectDTOs matching the search.
     */
    List<CulturalObjectDTO> searchObjects(SearchFormDTO searchCriteria);
}