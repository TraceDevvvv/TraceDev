package com.example.model;

/**
 * Details about a refreshment point designated by an agency.
 */
public class RefreshmentPointDetails {
    private String pointId;
    private String designation;
    private String location;
    private boolean isDesignated;
    
    public RefreshmentPointDetails() {}
    
    public RefreshmentPointDetails(String pointId, String designation, String location, boolean isDesignated) {
        this.pointId = pointId;
        this.designation = designation;
        this.location = location;
        this.isDesignated = isDesignated;
    }
    
    public String getPointId() {
        return pointId;
    }
    
    public void setPointId(String pointId) {
        this.pointId = pointId;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public boolean isDesignated() {
        return isDesignated;
    }
    
    public void setDesignated(boolean designated) {
        isDesignated = designated;
    }
}