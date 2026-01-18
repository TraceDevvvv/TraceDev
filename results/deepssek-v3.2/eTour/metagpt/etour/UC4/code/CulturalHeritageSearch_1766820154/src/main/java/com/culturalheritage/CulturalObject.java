package com.culturalheritage;

import java.time.Year;
import java.util.Objects;

/**
 * Represents a cultural object/heritage item in the system.
 * This class models the properties of cultural goods that can be searched.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String type; // e.g., artifact, monument, painting, sculpture
    private String era; // historical period
    private String location; // current location or origin
    private String description;
    private Year yearCreated; // year the object was created
    private String countryOfOrigin;
    private boolean isProtected; // whether it's a protected heritage item
    
    /**
     * Default constructor for creating a CulturalObject.
     */
    public CulturalObject() {
    }
    
    /**
     * Parameterized constructor for creating a CulturalObject with all properties.
     * 
     * @param id unique identifier
     * @param name name of the cultural object
     * @param type type/category of the object
     * @param era historical era/period
     * @param location current location or place of origin
     * @param description detailed description
     * @param yearCreated year the object was created
     * @param countryOfOrigin country of origin
     * @param isProtected whether it's a protected heritage item
     */
    public CulturalObject(String id, String name, String type, String era, 
                         String location, String description, Year yearCreated,
                         String countryOfOrigin, boolean isProtected) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.era = era;
        this.location = location;
        this.description = description;
        this.yearCreated = yearCreated;
        this.countryOfOrigin = countryOfOrigin;
        this.isProtected = isProtected;
    }
    
    /**
     * Simplified constructor for creating a CulturalObject with essential properties.
     * 
     * @param id unique identifier
     * @param name name of the cultural object
     * @param type type/category of the object
     * @param era historical era/period
     * @param location current location or place of origin
     */
    public CulturalObject(String id, String name, String type, String era, String location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.era = era;
        this.location = location;
        this.description = "";
        this.yearCreated = null;
        this.countryOfOrigin = "";
        this.isProtected = false;
    }
    
    // Getters and Setters
    
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getEra() {
        return era;
    }
    
    public void setEra(String era) {
        this.era = era;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Year getYearCreated() {
        return yearCreated;
    }
    
    public void setYearCreated(Year yearCreated) {
        this.yearCreated = yearCreated;
    }
    
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    
    public boolean isProtected() {
        return isProtected;
    }
    
    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }
    
    /**
     * Checks if this cultural object matches the given search criteria.
     * This is a helper method that can be used by the search service.
     * 
     * @param name name to match (null or empty means ignore)
     * @param type type to match (null or empty means ignore)
     * @param era era to match (null or empty means ignore)
     * @param location location to match (null or empty means ignore)
     * @return true if the object matches all non-null, non-empty criteria
     */
    public boolean matchesCriteria(String name, String type, String era, String location) {
        if (name != null && !name.isEmpty() && !this.name.toLowerCase().contains(name.toLowerCase())) {
            return false;
        }
        if (type != null && !type.isEmpty() && !this.type.equalsIgnoreCase(type)) {
            return false;
        }
        if (era != null && !era.isEmpty() && !this.era.equalsIgnoreCase(era)) {
            return false;
        }
        if (location != null && !location.isEmpty() && !this.location.toLowerCase().contains(location.toLowerCase())) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalObject that = (CulturalObject) o;
        return Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "CulturalObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", era='" + era + '\'' +
                ", location='" + location + '\'' +
                ", yearCreated=" + (yearCreated != null ? yearCreated.toString() : "Unknown") +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", isProtected=" + isProtected +
                '}';
    }
}