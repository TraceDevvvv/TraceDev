package com.example.smos.domain;

/**
 * Represents an academic year in the system.
 */
public class AcademicYear {
    private String id;
    private String year;

    /**
     * Constructs a new AcademicYear.
     * @param id The unique identifier for the academic year.
     * @param year The human-readable year string (e.g., "2023-2024").
     */
    public AcademicYear(String id, String year) {
        this.id = id;
        this.year = year;
    }

    /**
     * Gets the year string for this academic year.
     * @return The year string.
     */
    public String getYear() {
        return year;
    }

    /**
     * Gets the unique identifier for this academic year.
     * @return The academic year ID.
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AcademicYear{id='" + id + "', year='" + year + "'}";
    }
}