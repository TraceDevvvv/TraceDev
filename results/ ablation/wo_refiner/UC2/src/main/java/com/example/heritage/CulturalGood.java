package com.example.heritage;

import java.util.Date;

/**
 * Represents a cultural good entity.
 */
public class CulturalGood {
    private String id;
    private String name;
    private String description;
    private HeritageType type;
    private String location;
    private Date registrationDate;
    
    // Constructor without id (id may be generated later).
    public CulturalGood(String name, String description, HeritageType type, String location) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.registrationDate = new Date(); // set current date
    }
    
    // Constructor with all fields (including id) for flexibility.
    public CulturalGood(String id, String name, String description, HeritageType type, String location, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.registrationDate = registrationDate;
    }
    
    public CulturalGood(String name, String description, HeritageType type, String location, Date registrationDate) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.registrationDate = registrationDate;
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
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public HeritageType getType() {
        return type;
    }
    
    public void setType(HeritageType type) {
        this.type = type;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}