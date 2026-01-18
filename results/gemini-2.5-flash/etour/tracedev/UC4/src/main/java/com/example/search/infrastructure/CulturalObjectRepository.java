package com.example.search.infrastructure;

import com.example.search.domain.CulturalObject;
import com.example.search.domain.SearchCriteria;

import java.util.List;

/**
 * Interface defining the contract for repositories that manage CulturalObject persistence.
 * This interface abstracts the data access layer from the application logic.
 */
public interface CulturalObjectRepository {

    /**
     * Finds cultural objects based on the provided search criteria.
     *
     * @param criteria The search criteria to apply.
     * @return A list of CulturalObject instances that match the criteria.
     * @throws ETOURConnectionException if there is a problem connecting to the underlying data source (e.g., ETOUR).
     */
    List<CulturalObject> findByCriteria(SearchCriteria criteria);
}