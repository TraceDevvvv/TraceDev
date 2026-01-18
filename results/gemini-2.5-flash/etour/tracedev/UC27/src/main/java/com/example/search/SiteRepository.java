package com.example.search;

import java.util.List;
import java.util.Map;

/**
 * Interface defining the contract for Site data access operations.
 * This directly maps to the 'SiteRepository' interface in the UML diagram.
 */
public interface SiteRepository {

    /**
     * Finds a list of sites based on the provided criteria.
     * @param criteria A map of key-value pairs representing search criteria (e.g., "name": "example").
     * @return A list of Site objects matching the criteria.
     * @throws ConnectionError if there is an issue connecting to the data source.
     */
    List<Site> findByCriteria(Map<String, String> criteria) throws ConnectionError;
}