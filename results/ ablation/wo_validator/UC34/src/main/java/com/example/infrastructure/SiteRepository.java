package com.example.infrastructure;

import com.example.domain.SearchCriteria;
import com.example.domain.Site;
import java.util.List;

/**
 * Abstract repository for site data access.
 * Part of the Infrastructure Layer.
 */
public abstract class SiteRepository {
    /**
     * Finds sites matching the given criteria.
     * @param criteria domain search criteria
     * @return list of Site objects
     */
    public abstract List<Site> findByCriteria(SearchCriteria criteria);
}