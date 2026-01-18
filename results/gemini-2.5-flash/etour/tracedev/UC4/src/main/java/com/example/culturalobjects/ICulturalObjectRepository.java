package com.example.culturalobjects;

import java.util.List;

/**
 * REPOSITORY Interface: Defines the contract for accessing and managing CulturalObject data.
 * Adheres to the Repository pattern, abstracting data source details.
 */
public interface ICulturalObjectRepository {

    /**
     * Finds cultural objects based on the provided search criteria.
     * This method is designed to return either a List of CulturalObject or an ErrorResultDTO
     * based on the outcome of the underlying data source interaction, as depicted in the sequence diagram.
     *
     * @param criteria The search criteria to apply.
     * @return An Object which could be a List<CulturalObject> if successful, or an ErrorResultDTO if an error occurs (e.g., connection failure).
     */
    Object findByCriteria(SearchCriteria criteria);
}