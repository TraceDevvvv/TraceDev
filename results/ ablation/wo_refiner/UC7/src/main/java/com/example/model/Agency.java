package com.example.model;

/**
 * Agency that designates refreshment points.
 */
public class Agency {
    private String id;
    private String name;
    
    public Agency() {}
    
    public Agency(String id, String name) {
        this.id = id;
        this.name = name;
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
    
    /**
     * Gets the designated refreshment point for this agency.
     * 
     * @return RefreshmentPointDetails object or null if not designated
     */
    public RefreshmentPointDetails getDesignatedRefreshmentPoint() {
        // In a real implementation, this would fetch from a database
        // Returning a mock refreshment point for demonstration
        return new RefreshmentPointDetails("RP001", "Main Refreshment Point", "Building A, Floor 1", true);
    }
}