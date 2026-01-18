package com.example.service;

import com.example.dto.SiteDetailsDTO;

/**
 * Interface for site service operations.
 */
public interface SiteServiceInterface {
    /**
     * Retrieves site details by ID.
     * @param id the site ID
     * @return SiteDetailsDTO containing the site details
     */
    SiteDetailsDTO getSiteDetails(String id);
}