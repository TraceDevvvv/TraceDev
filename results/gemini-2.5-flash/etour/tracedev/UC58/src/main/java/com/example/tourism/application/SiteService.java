package com.example.tourism.application;

import com.example.tourism.data.ISiteRepository;
import com.example.tourism.domain.Site;
import com.example.tourism.dto.SiteDetailsDTO;

/**
 * Application Layer: Handles business logic related to site operations.
 * It orchestrates data retrieval from the repository and transforms entities into DTOs.
 * REQ-QR01: Performance considerations (e.g., caching, optimized queries) are implemented for quick response.
 * (Note: Actual caching/optimization logic is simplified for this example, but the intent is noted).
 */
public class SiteService {
    // Dependency on the Data Access Layer interface
    private ISiteRepository siteRepository;

    /**
     * Constructor for SiteService.
     * @param siteRepository The repository interface dependency.
     */
    public SiteService(ISiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Retrieves site details for a given site ID.
     * Implements part of the sequence diagram flow for `getSiteDetails`.
     *
     * @param siteId The ID of the site to retrieve.
     * @return A SiteDetailsDTO containing the site's information.
     * @throws ETOURConnectionException if there's an issue connecting to the data source (simulated).
     */
    public SiteDetailsDTO getSiteDetails(String siteId) throws ETOURConnectionException {
        System.out.println("SiteService: Requesting details for site ID: " + siteId);

        // Simulate a potential connection interruption (Exit Condition in Sequence Diagram)
        if (Math.random() < 0.1) { // 10% chance to simulate connection interruption
            throw new ETOURConnectionException("Simulated interruption during data retrieval for site: " + siteId);
        }

        // Call the data access layer to find the site by ID
        System.out.println("SiteService -> ISiteRepository: findById(" + siteId + ")");
        Site site = siteRepository.findById(siteId);
        System.out.println("SiteService <- ISiteRepository: Site entity received.");

        // REQ-QR01: Performance considerations (e.g., caching, optimized queries) are implemented for quick response.
        // In a real scenario, this might involve checking a cache before hitting the repository,
        // or the repository itself might use optimized queries. For this example, we'll assume
        // the `findById` operation is already optimized.

        // Transform the domain entity (Site) into a Data Transfer Object (SiteDetailsDTO)
        // This decouples the presentation layer from the domain model.
        // Traceability: m11 - Transform Site entity to SiteDetailsDTO
        System.out.println("SiteService: Transform Site entity to SiteDetailsDTO");
        if (site == null) {
            // Handle case where site is not found
            System.out.println("SiteService: Site with ID " + siteId + " not found.");
            // Return an empty or null DTO, or throw a specific 'SiteNotFoundException'
            // For now, return a DTO with only the ID indicating it wasn't found.
            SiteDetailsDTO dto = new SiteDetailsDTO();
            dto.id = siteId;
            dto.name = "Site Not Found";
            dto.description = "No details available for this site.";
            dto.location = "Unknown";
            dto.imageUrl = "N/A";
            return dto;
        }

        SiteDetailsDTO siteDetailsDTO = new SiteDetailsDTO();
        siteDetailsDTO.id = site.id;
        siteDetailsDTO.name = site.name;
        siteDetailsDTO.description = site.description;
        siteDetailsDTO.location = site.location;
        siteDetailsDTO.imageUrl = site.imageUrl;

        System.out.println("SiteService: Successfully retrieved and transformed site details.");
        return siteDetailsDTO;
    }
}