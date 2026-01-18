package com.culturalheritage.model;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * CulturalHeritage entity class representing a cultural heritage object.
 * This class models the data for a cultural heritage item including its
 * attributes like name, type, location, era, description, agency code,
 * and registration date.
 */
public class CulturalHeritage {
    
    private String id;
    private String name;
    private String type;
    private String location;
    private String era;
    private String description;
    private String agencyCode;
    private Date registrationDate;
    
    /**
     * Default constructor.
     * Initializes the object with a generated UUID and current date.
     */
    public CulturalHeritage() {
        this.id = UUID.randomUUID().toString();
        this.registrationDate = new Date();
    }
    
    /**
     * Parameterized constructor for creating a CulturalHeritage object.
     * Automatically generates an ID and sets the registration date to current date.
     * 
     * @param name The name of the cultural heritage
     * @param type The type/category of the cultural heritage
     * @param location The location where the cultural heritage is found
     * @param era The historical era of the cultural heritage
     * @param description Description of the cultural heritage
     * @param agencyCode Code of the agency registering the cultural heritage
     */
    public CulturalHeritage(String name, String type, String location, 
                           String era, String description, String agencyCode) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.location = location;
        this.era = era;
        this.description = description;
        this.agencyCode = agencyCode;
        this.registrationDate = new Date();
    }
    
    // Getters and setters
    
    /**
     * @return The unique identifier of the cultural heritage
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the unique identifier.
     * Note: Should only be used when loading from database.
     * 
     * @param id The unique identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return The name of the cultural heritage
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the cultural heritage.
     * 
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return The type/category of the cultural heritage
     */
    public String getType() {
        return type;
    }
    
    /**
     * Sets the type/category of the cultural heritage.
     * 
     * @param type The type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return The location of the cultural heritage
     */
    public String getLocation() {
        return location;
    }
    
    /**
     * Sets the location of the cultural heritage.
     * 
     * @param location The location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * @return The historical era of the cultural heritage
     */
    public String getEra() {
        return era;
    }
    
    /**
     * Sets the historical era of the cultural heritage.
     * 
     * @param era The era to set
     */
    public void setEra(String era) {
        this.era = era;
    }
    
    /**
     * @return The description of the cultural heritage
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description of the cultural heritage.
     * 
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return The agency code that registered the cultural heritage
     */
    public String getAgencyCode() {
        return agencyCode;
    }
    
    /**
     * Sets the agency code.
     * 
     * @param agencyCode The agency code to set
     */
    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }
    
    /**
     * @return The registration date of the cultural heritage
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }
    
    /**
     * Sets the registration date.
     * Note: Should only be used when loading from database.
     * 
     * @param registrationDate The registration date to set
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    /**
     * Returns a string representation of the CulturalHeritage object.
     * 
     * @return String representation including all attributes
     */
    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", era='" + era + '\'' +
                ", description='" + description + '\'' +
                ", agencyCode='" + agencyCode + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
    
    /**
     * Compares this CulturalHeritage object with another object for equality.
     * Two CulturalHeritage objects are equal if they have the same id,
     * or if they have the same name and location (for duplicate checking).
     * 
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        CulturalHeritage that = (CulturalHeritage) obj;
        
        // First check by ID (for database equality)
        if (id != null && that.id != null && id.equals(that.id)) {
            return true;
        }
        
        // Then check by name and location combination (for duplicate detection)
        // This meets the quality requirement of not accepting duplicates
        boolean nameEquals = Objects.equals(name, that.name);
        boolean locationEquals = Objects.equals(location, that.location);
        
        return nameEquals && locationEquals;
    }
    
    /**
     * Returns a hash code value for this CulturalHeritage object.
     * The hash code is based on the id, name, and location.
     * 
     * @return The hash code value
     */
    @Override
    public int hashCode() {
        // Use id if available, otherwise use name and location for duplicate checking
        if (id != null) {
            return Objects.hash(id);
        }
        return Objects.hash(name, location);
    }
}