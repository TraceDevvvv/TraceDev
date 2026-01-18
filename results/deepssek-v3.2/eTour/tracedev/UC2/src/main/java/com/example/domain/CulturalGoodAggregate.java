
package com.example.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

/**
 * The main aggregate representing a cultural good.
 * Removed dependency to CulturalGoodRepository as per audit report #7.
 */
public class CulturalGoodAggregate {
    private UUID id;
    private String name;
    private String description;
    private CulturalGoodType type;
    private Location location;
    private Map<String, Object> otherAttributes;
    
    public CulturalGoodAggregate(String name, String description, CulturalGoodType type, Location location) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
        this.otherAttributes = new HashMap<>();
    }
    
    /**
     * Validates the data of the cultural good.
     * @return ValidationResult indicating success or failure with error messages.
     */
    public ValidationResult validate() {
        List<String> errors = new ArrayList<>();
        
        if (name == null || name.trim().isEmpty()) {
            errors.add("Name is required.");
        }
        if (description == null || description.trim().isEmpty()) {
            errors.add("Description is required.");
        }
        if (type == null) {
            errors.add("Type is required.");
        }
        if (location == null) {
            errors.add("Location is required.");
        } else {
            if (location.getAddress() == null || location.getAddress().trim().isEmpty()) {
                errors.add("Address is required.");
            }
            GeoPoint coords = location.getCoordinates();
            if (coords == null) {
                errors.add("Coordinates are required.");
            }
        }
        
        boolean isValid = errors.isEmpty();
        return new ValidationResult(isValid, errors);
    }
    
    /**
     * Checks if this cultural good is a duplicate using the repository.
     * @param repository the repository to query
     * @return true if a duplicate exists, false otherwise
     */
    public boolean isDuplicate(CulturalGoodRepository repository) {
        return repository.existsByNameAndLocation(name, location);
    }
    
    public UUID getId() {
        return id;
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
    
    public Map<String, Object> getOtherAttributes() {
        return otherAttributes;
    }
    
    // Interface for repository dependency
    public interface CulturalGoodRepository {
        boolean existsByNameAndLocation(String name, Location location);
    }
}
