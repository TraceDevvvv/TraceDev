package com.school.monitoring.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a school year, typically defined by a start and end date,
 * or simply by a descriptive string.
 * For this application, a simple string representation is used for clarity
 * and to align with the reporting requirements.
 */
public class SchoolYear {
    private String yearIdentifier; // e.g., "2023-2024"

    /**
     * Constructs a new SchoolYear with a given identifier.
     *
     * @param yearIdentifier A string representing the school year (e.g., "2023-2024").
     * @throws IllegalArgumentException if the yearIdentifier is null or empty.
     */
    public SchoolYear(String yearIdentifier) {
        if (yearIdentifier == null || yearIdentifier.trim().isEmpty()) {
            throw new IllegalArgumentException("School year identifier cannot be null or empty.");
        }
        this.yearIdentifier = yearIdentifier;
    }

    /**
     * Returns the identifier for this school year.
     *
     * @return The string identifier of the school year.
     */
    public String getYearIdentifier() {
        return yearIdentifier;
    }

    /**
     * Sets the identifier for this school year.
     *
     * @param yearIdentifier The new string identifier for the school year.
     * @throws IllegalArgumentException if the yearIdentifier is null or empty.
     */
    public void setYearIdentifier(String yearIdentifier) {
        if (yearIdentifier == null || yearIdentifier.trim().isEmpty()) {
            throw new IllegalArgumentException("School year identifier cannot be null or empty.");
        }
        this.yearIdentifier = yearIdentifier;
    }

    @Override
    public String toString() {
        return yearIdentifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolYear that = (SchoolYear) o;
        return Objects.equals(yearIdentifier, that.yearIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearIdentifier);
    }
}