package com.example.convention;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a historical convention event.
 * This is a domain entity containing all raw data.
 */
public class Convention {
    private String id;
    private String name;
    private Date date;
    private String description;
    private String pointOfRestId;

    /**
     * Constructs a new Convention instance.
     *
     * @param id The unique identifier for the convention.
     * @param name The name of the convention.
     * @param date The date when the convention took place.
     * @param description A brief description of the convention.
     * @param pointOfRestId The ID of the Point of Rest associated with this convention.
     */
    public Convention(String id, String name, Date date, String description, String pointOfRestId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.pointOfRestId = pointOfRestId;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        // Return a defensive copy to prevent external modification of the internal Date object
        return (date != null) ? new Date(date.getTime()) : null;
    }

    public String getDescription() {
        return description;
    }

    public String getPointOfRestId() {
        return pointOfRestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Convention that = (Convention) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Convention{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", date=" + date +
               ", description='" + description + '\'' +
               ", pointOfRestId='" + pointOfRestId + '\'' +
               '}';
    }
}