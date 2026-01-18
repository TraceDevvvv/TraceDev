package com.example.application.port.out;

import com.example.domain.CulturalObject;
import com.example.domain.SearchCriteria;
import java.util.List;

/**
 * Repository port for accessing cultural object data (hexagonal architecture).
 */
public interface CulturalObjectRepository {
    /**
     * Finds cultural objects matching the given criteria.
     * @param criteria the search criteria
     * @return list of matching cultural objects
     */
    List<CulturalObject> findByCriteria(SearchCriteria criteria);
}