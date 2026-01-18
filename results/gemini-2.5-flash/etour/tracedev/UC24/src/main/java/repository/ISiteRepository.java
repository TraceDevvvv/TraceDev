package com.example.repository;

import com.example.model.Site;
import com.example.service.NetworkConnectionException;

import java.util.List;

/**
 * Interface for operations related to Site data persistence.
 */
public interface ISiteRepository {
    /**
     * Retrieves a Site by its unique identifier.
     * @param id The ID of the site to retrieve.
     * @return The Site object if found, null otherwise.
     */
    Site getSiteById(String id);

    /**
     * Retrieves all available Sites.
     * @return A list of all Site objects.
     * @throws NetworkConnectionException if there's an issue connecting to the data source.
     */
    List<Site> getAllSites() throws NetworkConnectionException;
}