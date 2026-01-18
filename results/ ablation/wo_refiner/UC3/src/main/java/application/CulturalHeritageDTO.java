package application;

import domain.CulturalHeritage;

/**
 * Data Transfer Object for CulturalHeritage.
 * Used to pass data between layers without exposing domain logic.
 */
public class CulturalHeritageDTO {
    private String id;
    private String name;
    private String description;
    private String type;
    private String location;
    // other DTO attributes

    public CulturalHeritageDTO() {
    }

    public CulturalHeritageDTO(CulturalHeritage domainEntity) {
        this.id = domainEntity.getId();
        this.name = domainEntity.getName();
        this.description = domainEntity.getDescription();
        this.type = domainEntity.getType();
        this.location = domainEntity.getLocation();
    }

    // Getters and setters
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}