package com.etour.search;

import java.util.List;

/**
 * Interface for site repository implementations.
 * Provides methods for finding sites based on criteria and location.
 */
public interface ISiteRepository {
    
    /**
     * Finds sites matching the given criteria and location.
     * @param criteria Search criteria.
     * @param location User's current location.
     * @return List of matching sites.
     */
    List<Site> findByCriteria(SearchCriteria criteria, Location location);
    
    /**
     * Returns all sites in the repository.
     * @return List of all sites.
     */
    List<Site> findAll();
    
    /**
     * Tests the connection to the data source.
     * @return true if connection is successful, false otherwise.
     */
    boolean testConnection();
}