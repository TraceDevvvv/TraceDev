package com.example.point;

/**
 * Represents a refreshment point that contains banners.
 */
public class RefreshmentPoint {
    
    private int id;
    private String name;
    
    /**
     * Constructs a RefreshmentPoint with given ID and name.
     * @param id the point ID
     * @param name the point name
     */
    public RefreshmentPoint(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * Returns the point ID.
     * @return the ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Returns the point name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets a new name for the refreshment point.
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }
}