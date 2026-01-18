package com.example.presentation;

import com.example.domain.CulturalGoodType;

/**
 * Data collected from the insert form.
 * Added to satisfy requirement: Flow of Events: 3. Agency Operator fills out the form...
 */
public class FormData {
    private String name;
    private String description;
    private CulturalGoodType type;
    private LocationData locationData;
    
    public FormData(String name, String description, CulturalGoodType type, LocationData locationData) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.locationData = locationData;
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
    
    public LocationData getLocationData() {
        return locationData;
    }
}