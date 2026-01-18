package com.example.model;

import com.example.enums.ConventionStatus;

/**
 * Convention entity representing a business agreement.
 */
public class Convention {
    private String id;
    private String name;
    private ConventionStatus status;
    private RefreshmentPointDetails refreshmentPointDetails;
    
    public Convention() {
        this.status = ConventionStatus.PENDING;
    }
    
    public Convention(String id, String name, ConventionStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public ConventionStatus getStatus() {
        return status;
    }
    
    public void setStatus(ConventionStatus status) {
        this.status = status;
    }
    
    public RefreshmentPointDetails getRefreshmentPointDetails() {
        return refreshmentPointDetails;
    }
    
    public void setRefreshmentPointDetails(RefreshmentPointDetails refreshmentPointDetails) {
        this.refreshmentPointDetails = refreshmentPointDetails;
    }
    
    /**
     * Activates the convention by changing its status to ACTIVE.
     */
    public void activate() {
        this.status = ConventionStatus.ACTIVE;
        // Additional activation logic could be added here
    }
    
    /**
     * Loads data for this convention based on the request ID.
     * 
     * @param requestId The request identifier
     * @return The Convention object with loaded data
     */
    public Convention loadData(String requestId) {
        // In a real implementation, this would load data from a repository
        // For demonstration, we'll set some mock data
        this.id = requestId;
        this.name = "Convention for Request " + requestId;
        this.refreshmentPointDetails = new RefreshmentPointDetails("RP001", "Designated Point", "Main Hall", true);
        
        return this;
    }
}