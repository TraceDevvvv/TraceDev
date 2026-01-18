package com.etour.repositories;

import com.etour.adapters.SearchQuery;
import com.etour.entities.CulturalObject;
import java.util.List;

/**
 * Repository port for accessing cultural objects.
 * Part of the External layer interface.
 */
public interface CulturalObjectRepository {
    /**
     * Finds cultural objects matching the given criteria.
     * @param query the search criteria
     * @return list of matching cultural objects
     */
    List<CulturalObject> findByCriteria(SearchQuery query);

    /**
     * Counts cultural objects matching the given criteria.
     * @param query the search criteria
     * @return count of matching objects
     */
    int countByCriteria(SearchQuery query);
}