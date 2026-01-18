package com.example.repository;

import com.example.model.CulturalHeritageEntity;
import com.example.model.HeritageSearchCriteria;
import java.util.List;

/**
 * Repository interface for data access operations on cultural heritage.
 */
public interface CulturalHeritageRepository {

    /**
     * Finds a cultural heritage entity by its ID.
     * @param id the unique identifier
     * @return the found entity, or null if not found
     */
    CulturalHeritageEntity findById(String id);

    /**
     * Finds all cultural heritage entities matching the given criteria.
     * @param criteria the search criteria
     * @return list of matching entities
     */
    List<CulturalHeritageEntity> findAllByCriteria(HeritageSearchCriteria criteria);
}