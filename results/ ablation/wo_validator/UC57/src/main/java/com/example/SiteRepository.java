package com.example;

import java.util.List;

/**
 * Repository interface for site data access.
 */
public interface SiteRepository {
    /**
     * Finds sites based on criteria and location.
     * @param criteria the search criteria.
     * @param location the reference location.
     * @return a list of matching sites.
     */
    List<Site> findByCriteria(SearchCriteria criteria, Location location);
}