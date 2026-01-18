package com.cultural.domain.model;

import java.util.Objects;

/**
 * CulturalObject domain class representing a cultural heritage object.
 * Implements core domain logic and validation.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String description;
    private String type;
    private String location;

    /**
     * Constructor for CulturalObject.
     */
    public CulturalObject(String id, String name, String description, String type, String location) {
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

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    /**
     * Validates the cultural object.
     * Assumption: basic validation that all fields are non-null and non-empty.
     * @return true if valid, false otherwise.
     */
    public boolean validate() {
        return id != null && !id.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               description != null && !description.trim().isEmpty() &&
               type != null && !type.trim().isEmpty() &&
               location != null && !location.trim().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalObject that = (CulturalObject) o;
        return Objects.equals(name, that.name) && Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}