package com.example.digitalregister.model;

/**
 * Represents an academic year in the system.
 */
public class AcademicYear {
    private String id;
    private int year;
    private String description;

    /**
     * Constructs a new AcademicYear instance.
     * @param id A unique identifier for the academic year.
     * @param year The year number (e.g., 2023).
     * @param description A descriptive name for the academic year (e.g., "2023-2024").
     */
    public AcademicYear(String id, int year, String description) {
        this.id = id;
        this.year = year;
        this.description = description;
    }

    /**
     * Gets the unique identifier for the academic year.
     * @return The academic year ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the year number.
     * @return The year.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the descriptive name for the academic year.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
               "id='" + id + '\'' +
               ", year=" + year +
               ", description='" + description + '\'' +
               '}';
    }
}