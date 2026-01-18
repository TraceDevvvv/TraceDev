package com.example.model;

/**
 * Represents a tourist site.
 */
public class Site {
    private int id;
    private String name;
    private String description;
    
    public Site(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
     * Returns the site details.
     * @return description of the site
     */
    public String getDetails() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return "Site{id=" + id + ", name='" + name + "', description='" + description + "'}";
    }
}