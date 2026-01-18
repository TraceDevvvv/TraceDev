package com.example.application.port.in;

import com.example.domain.CulturalObject;
import com.example.domain.SearchCriteria;
import java.util.List;

/**
 * Port interface for the search use case (hexagonal architecture).
 * Defines the contract for executing cultural object searches.
 */
public interface SearchUseCase {
    /**
     * Executes a search based on the provided criteria.
     * @param criteria the search criteria
     * @return list of matching cultural objects
     */
    List<CulturalObject> execute(SearchCriteria criteria);
}