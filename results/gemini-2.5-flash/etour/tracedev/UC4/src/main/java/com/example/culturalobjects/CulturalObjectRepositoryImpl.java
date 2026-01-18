package com.example.culturalobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository Implementation: Concrete implementation of ICulturalObjectRepository.
 * Handles interaction with the ETOURGateway to fetch cultural object data.
 * Manages the conversion between application-specific criteria and gateway-specific queries.
 */
public class CulturalObjectRepositoryImpl implements ICulturalObjectRepository {

    // Dependency: ETOURGateway for external system interaction
    private final ETOURGateway etourGateway;

    /**
     * Constructor for CulturalObjectRepositoryImpl.
     * @param etourGateway The ETOURGateway instance to use for data fetching.
     */
    public CulturalObjectRepositoryImpl(ETOURGateway etourGateway) {
        this.etourGateway = etourGateway;
    }

    /**
     * Finds cultural objects based on the provided search criteria by interacting with the ETOURGateway.
     * Implements the logic for converting criteria to ETOUR query format and handling ETOUR responses,
     * including potential connection errors as per the sequence diagram.
     *
     * @param criteria The search criteria to apply.
     * @return A List<CulturalObject> if successful, or an ErrorResultDTO if a connection error occurs.
     */
    @Override
    public Object findByCriteria(SearchCriteria criteria) {
        System.out.println("[Repository] Converting SearchCriteria to ETOUR query map...");
        // Convert SearchCriteria to a Map suitable for ETOURGateway
        Map<String, String> query = new HashMap<>();
        if (criteria.getKeywords() != null && !criteria.getKeywords().isEmpty()) {
            query.put("keywords", criteria.getKeywords());
        }
        if (criteria.getCategory() != null && !criteria.getCategory().isEmpty()) {
            query.put("category", criteria.getCategory());
        }
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            query.put("location", criteria.getLocation());
        }

        System.out.println("[Repository] Calling ETOURGateway.searchETOUR with query: " + query);
        String etourRawResponse;
        try {
            // Call ETOUR Gateway to get raw response
            etourRawResponse = etourGateway.searchETOUR(query);
        } catch (ETOURGateway.ETOURConnectionException e) {
            // Handle ETOUR connection interruption as per sequence diagram
            System.err.println("[Repository] ETOUR connection failed: " + e.getMessage());
            return new ErrorResultDTO("ETOUR_CONNECTION_FAILED", "Failed to connect to ETOUR server: " + e.getMessage());
        }

        System.out.println("[Repository] ETOURGateway returned raw response. Parsing...");
        // Parse the raw response into a list of CulturalObject
        List<CulturalObject> culturalObjects = etourGateway.parseETOURResponse(etourRawResponse);

        if (culturalObjects.isEmpty()) {
            System.out.println("[Repository] No cultural objects found for the given criteria.");
        } else {
            System.out.println("[Repository] Found " + culturalObjects.size() + " cultural objects.");
        }

        return culturalObjects;
    }
}