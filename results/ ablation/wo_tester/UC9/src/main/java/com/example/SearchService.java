package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service that orchestrates the search process.
 * Quality requirement: searchPointsOfRest must complete within 15 seconds.
 */
public class SearchService {
    private boolean connectionStatus;
    private PointOfRestRepository pointOfRestRepository;

    public SearchService(PointOfRestRepository pointOfRestRepository) {
        this.pointOfRestRepository = pointOfRestRepository;
        this.connectionStatus = true;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    /**
     * Creates a specification from the given criteria.
     * @param criteria the search criteria
     * @return a PointOfRestSpecification
     */
    public PointOfRestSpecification createSpecification(Map<String, Object> criteria) {
        return new SearchCriteriaSpecification(criteria);
    }

    /**
     * Searches points of rest based on a specification.
     * Must complete within 15 seconds (quality requirement).
     * @param specification the search specification
     * @return list of PointOfRestDTOs
     */
    public List<PointOfRestDTO> searchPointsOfRest(PointOfRestSpecification specification) {
        long startTime = System.currentTimeMillis();
        // Check server connection
        if (!checkServerConnection()) {
            // Return empty list to indicate connection error
            return new ArrayList<>();
        }
        // Fetch points from repository
        List<PointOfRest> points = pointOfRestRepository.findAll(specification);
        // Convert to DTOs
        List<PointOfRestDTO> dtos = convertToDTOs(points);
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Search completed in " + elapsed + " ms");
        // Ensure it meets the 15-second requirement (log warning if not)
        if (elapsed > 15000) {
            System.err.println("Warning: Search exceeded 15-second quality requirement.");
        }
        return dtos;
    }

    /**
     * Converts a list of PointOfRest entities to DTOs.
     * @param points the list of entities
     * @return list of DTOs
     */
    public List<PointOfRestDTO> convertToDTOs(List<PointOfRest> points) {
        List<PointOfRestDTO> dtos = new ArrayList<>();
        for (PointOfRest point : points) {
            PointOfRestDTO dto = new PointOfRestDTO(
                    point.getId(),
                    point.getName(),
                    point.getLocation(),
                    point.getAmenities()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * Checks the server connection status.
     * @return true if connected, false otherwise
     */
    public boolean checkServerConnection() {
        // Delegate to repository's server or internal status
        return connectionStatus;
    }
}