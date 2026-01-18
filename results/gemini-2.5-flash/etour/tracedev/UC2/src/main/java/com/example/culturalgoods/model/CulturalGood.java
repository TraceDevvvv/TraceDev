package com.example.culturalgoods.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a cultural good entity in the system.
 * This class stores detailed information about a cultural good.
 */
public class CulturalGood {
    private String id; // Unique identifier for the cultural good
    private String name; // Name of the cultural good
    private String description; // Description of the cultural good
    private LocalDate acquisitionDate; // Date when the cultural good was acquired

    /**
     * Constructs a new CulturalGood with the specified details.
     *
     * @param id The unique identifier for the cultural good.
     * @param name The name of the cultural good.
     * @param description The description of the cultural good.
     * @param acquisitionDate The acquisition date of the cultural good.
     */
    public CulturalGood(String id, String name, String description, LocalDate acquisitionDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Gets the unique identifier of the cultural good.
     * @return The ID of the cultural good.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the cultural good.
     * This is typically set during creation or persistence.
     * @param id The ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the cultural good.
     * @return The name of the cultural good.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cultural good.
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description of the cultural good.
     * @return The description of the cultural good.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the cultural good.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the acquisition date of the cultural good.
     * @return The acquisition date.
     */
    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    /**
     * Sets the acquisition date of the cultural good.
     * @param acquisitionDate The acquisition date to set.
     */
    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    /**
     * Compares this CulturalGood to the specified object. The result is true if and only if
     * the argument is not null and is a CulturalGood object that has the same id.
     * If id is null, it compares based on name.
     * @param obj The object to compare with.
     * @return true if the objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CulturalGood that = (CulturalGood) obj;
        // If ID is set, use ID for equality, otherwise use name (as name uniqueness is checked before ID generation)
        if (id != null) {
            return Objects.equals(id, that.id);
        }
        return Objects.equals(name, that.name) &&
               Objects.equals(description, that.description) &&
               Objects.equals(acquisitionDate, that.acquisitionDate);
    }

    /**
     * Returns a hash code for this CulturalGood. The hash code is based on the id
     * if available, otherwise on the name, description, and acquisition date.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        if (id != null) {
            return Objects.hash(id);
        }
        return Objects.hash(name, description, acquisitionDate);
    }

    @Override
    public String toString() {
        return "CulturalGood{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", acquisitionDate=" + acquisitionDate +
               '}';
    }
}