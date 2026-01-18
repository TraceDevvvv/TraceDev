package com.example.convention;

import java.util.Date;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for Convention.
 * This class is used to transfer convention data to the presentation layer,
 * ensuring only necessary information is exposed (REQ8: accuracy).
 */
public class ConventionDTO {
    private String id;
    private String name;
    private Date date;
    private String description;

    /**
     * Constructs a new ConventionDTO instance.
     *
     * @param id The unique identifier for the convention.
     * @param name The name of the convention.
     * @param date The date when the convention took place.
     * @param description A brief description of the convention.
     */
    public ConventionDTO(String id, String name, Date date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConventionDTO that = (ConventionDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ConventionDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", date=" + date +
               ", description='" + description + '\'' +
               '}';
    }
}