package com.example.refreshmentpoint;

import java.util.Objects;

/**
 * Represents a refreshment point with its details.
 * This class holds information such as the point's ID, name, location, and a brief description.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private String description;
    private String type; // e.g., "Restaurant", "Cafe", "Bar"
    private double rating; // e.g., 4.5

    /**
     * Constructs a new RefreshmentPoint.
     *
     * @param id The unique identifier for the refreshment point.
     * @param name The name of the refreshment point.
     * @param location The physical location or address of the refreshment point.
     * @param description A brief description of the refreshment point.
     * @param type The type of refreshment point (e.g., Restaurant, Cafe).
     * @param rating The average rating of the refreshment point.
     */
    public RefreshmentPoint(String id, String name, String location, String description, String type, double rating) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.type = type;
        this.rating = rating;
    }

    // Getters for all properties

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public double getRating() {
        return rating;
    }

    // Setters for all properties (optional, depending on mutability requirements)
    // For this use case, we assume RefreshmentPoint objects are immutable once created,
    // but setters are included for completeness if future modifications are needed.

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Provides a string representation of the RefreshmentPoint object.
     *
     * @return A formatted string containing the details of the refreshment point.
     */
    @Override
    public String toString() {
        return "RefreshmentPoint Details:\n" +
               "  ID: " + id + "\n" +
               "  Name: " + name + "\n" +
               "  Location: " + location + "\n" +
               "  Type: " + type + "\n" +
               "  Rating: " + rating + "/5.0\n" +
               "  Description: " + description;
    }

    /**
     * Compares this RefreshmentPoint to the specified object. The result is true if and only if
     * the argument is not null and is a RefreshmentPoint object that has the same ID as this object.
     *
     * @param o The object to compare this RefreshmentPoint against.
     * @return true if the given object represents a RefreshmentPoint equivalent to this refreshment point, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}