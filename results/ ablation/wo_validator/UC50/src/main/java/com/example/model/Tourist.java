package com.example.model;

import java.util.List;

/**
 * Represents a Tourist with authentication and site request capabilities.
 */
public class Tourist {
    private int id;
    private String name;
    
    public Tourist(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Authenticates the tourist. For simplicity, returns true.
     */
    public boolean authenticate() {
        // In a real implementation, authentication logic would be here
        return true;
    }
    
    /**
     * Requests visited sites via the controller.
     * @return a SiteList (assumed to be a list of Site objects)
     */
    public List<Site> requestVisitedSites() {
        // Placeholder: In a real implementation, this would use a proper controller
        return List.of();
    }
}