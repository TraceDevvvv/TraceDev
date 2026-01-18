package com.example.service;

import com.example.dto.SiteDetailsDTO;
import com.example.model.SiteEntity;
import com.example.repository.SiteRepositoryInterface;
import com.example.exception.ConnectionError;

/**
 * Service implementation for site operations.
 */
public class SiteService implements SiteServiceInterface {
    private SiteRepositoryInterface siteRepository;

    public SiteService(SiteRepositoryInterface siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Checks the database connection.
     * @return true if connection is available, false otherwise.
     */
    private boolean checkConnection() {
        // Simulated connection check; in reality, this would check a database connection.
        // For this example, we assume connection is always available.
        return true;
    }

    @Override
    public SiteDetailsDTO getSiteDetails(String id) {
        // Check connection as per sequence diagram
        if (!checkConnection()) {
            // Connection interrupted: create and throw a ConnectionError
            ConnectionError error = new ConnectionError("Server ETOUR connection interrupted");
            throw error;
        }

        // Connection available: retrieve site entity
        SiteEntity entity = siteRepository.findById(id);
        if (entity == null) {
            throw new RuntimeException("Site not found for ID: " + id);
        }

        // Convert entity to DTO (renamed from addToResponse() to toDTO())
        return entity.toDTO();
    }
}