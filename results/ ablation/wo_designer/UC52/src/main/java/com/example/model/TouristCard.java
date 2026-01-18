package com.example.model;

/**
 * Represents a tourist card that is associated with a particular site.
 */
public class TouristCard {
    
    private String touristId;
    private Site currentSite;
    
    public TouristCard(String touristId, Site currentSite) {
        this.touristId = touristId;
        this.currentSite = currentSite;
    }
    
    public String getTouristId() {
        return touristId;
    }
    
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    
    public Site getCurrentSite() {
        return currentSite;
    }
    
    public void setCurrentSite(Site currentSite) {
        this.currentSite = currentSite;
    }
}