/*
Entity class representing a Cultural Object.
*/
package com.chatdev.modifybeneconaturale.model;
import java.io.Serializable;
import java.util.Objects;
public class CulturalObject implements Serializable {
    private static final long serialVersionUID = 1L; // For serialization
    private String id;
    private String name;
    private String description;
    private String location;
    private double value; // Example attribute, could be monetary value or importance score
    /**
     * Default constructor for CulturalObject.
     */
    public CulturalObject() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.location = "";
        this.value = 0.0;
    }
    /**
     * Constructs a CulturalObject with specified details.
     *
     * @param id The unique identifier of the cultural object.
     * @param name The name of the cultural object.
     * @param description A brief description of the cultural object.
     * @param location The current location of the cultural object.
     * @param value The estimated value or significance of the cultural object.
     */
    public CulturalObject(String id, String name, String description, String location, double value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.value = value;
    }
    // Getters and Setters for all fields
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name;
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
}