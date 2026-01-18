package com.example.model;

import com.example.model.RestPoint;
import java.util.List;

/**
 * Represents an Agency Operator actor in the system.
 * Follows REQ-003: Agency Operator interacts with UIController.
 */
public class AgencyOperator {
    private String id;
    private String name;
    private String agency;

    public AgencyOperator(String id, String name, String agency) {
        this.id = id;
        this.name = name;
        this.agency = agency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Search for rest points for the agency (REQ-006).
     * In a real implementation, this would call a service.
     */
    public List<RestPoint> searchRestPoints(String agencyId) {
        // This is a stub; actual implementation would query a service.
        return List.of();
    }

    public RestPoint selectRestPoint(String restPointId) {
        // This is a stub; actual implementation would retrieve from a service.
        return new RestPoint(restPointId, "Sample Rest Point", "Description", 5);
    }

    /**
     * Initiates the banner insertion process.
     */
    public void insertBanner() {
        // This method would typically interact with UIController.
        // Implementation left to the caller (e.g., main flow).
    }
}