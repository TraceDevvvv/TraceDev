package com.example.application;

import com.example.domain.CulturalGoodType;
import com.example.domain.Location;

/**
 * Command object for inserting a new cultural good.
 */
public class InsertCulturalGoodCommand {
    private String name;
    private String description;
    private CulturalGoodType type;
    private Location location;
    
    public InsertCulturalGoodCommand(String name, String description, CulturalGoodType type, Location location) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public CulturalGoodType getType() {
        return type;
    }
    
    public Location getLocation() {
        return location;
    }
}