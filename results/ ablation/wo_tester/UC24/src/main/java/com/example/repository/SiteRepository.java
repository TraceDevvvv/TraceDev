package com.example.repository;

import com.example.model.Site;
import com.example.exception.DatabaseException;
import java.util.List;

/**
 * Repository interface for Site entities.
 */
public interface SiteRepository {
    /**
     * Retrieves all sites from the database.
     * @return List of Site objects
     * @throws DatabaseException if a database error occurs (e.g., connection interruption)
     */
    List<Site> findAllSites() throws DatabaseException;

    /**
     * Checks the connection status to the database.
     * @return true if connected, false otherwise
     */
    boolean isConnected();
}