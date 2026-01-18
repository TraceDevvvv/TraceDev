package domain;

import java.util.Objects;

/**
 * Domain entity representing a Cultural Heritage item.
 * Contains core business attributes and validation logic.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private String type;
    private String location;
    // other domain attributes could be added here

    public CulturalHeritage(String id, String name, String description, String type, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    public String getId() {
        return id;
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

    /**
     * Validates this domain object for modification.
     * Assumes basic validation: id, name, location must not be empty.
     * @return true if valid, false otherwise
     */
    public boolean validateForModification() {
        if (id == null || id.trim().isEmpty()) return false;
        if (name == null || name.trim().isEmpty()) return false;
        if (location == null || location.trim().isEmpty()) return false;
        // additional domain rules could be added here
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}