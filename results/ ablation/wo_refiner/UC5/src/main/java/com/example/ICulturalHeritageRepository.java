package com.example;

import java.util.List;

/**
 * Interface for accessing CulturalHeritage data.
 */
public interface ICulturalHeritageRepository {
    /**
     * Finds all cultural heritage items matching the given criteria.
     * @param criteria search criteria
     * @return list of matching CulturalHeritage objects
     */
    List<CulturalHeritage> findAllByCriteria(String criteria);

    /**
     * Finds a cultural heritage item by its ID.
     * @param id the unique identifier
     * @return CulturalHeritage object if found, null otherwise
     */
    CulturalHeritage findById(String id);
}