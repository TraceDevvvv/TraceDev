package com.reportcard.system;

import java.util.Objects;

/**
 * Represents an academic year in the Report Card System.
 * Each academic year has a unique identifier (e.g., "2023-2024").
 */
public class AcademicYear {
    private final String yearIdentifier;

    /**
     * Constructs a new AcademicYear with the specified identifier.
     *
     * @param yearIdentifier A string representing the academic year (e.g., "2023-2024").
     *                       Must not be null or empty.
     * @throws IllegalArgumentException if yearIdentifier is null or empty.
     */
    public AcademicYear(String yearIdentifier) {
        if (yearIdentifier == null || yearIdentifier.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year identifier cannot be null or empty.");
        }
        this.yearIdentifier = yearIdentifier;
    }

    /**
     * Returns the identifier of this academic year.
     *
     * @return The academic year identifier.
     */
    public String getYearIdentifier() {
        return yearIdentifier;
    }

    /**
     * Overrides the toString method to provide a meaningful string representation of the AcademicYear object.
     *
     * @return A string in the format "AcademicYear[yearIdentifier='YYYY-YYYY']".
     */
    @Override
    public String toString() {
        return "AcademicYear[yearIdentifier='" + yearIdentifier + "']";
    }

    /**
     * Compares this AcademicYear object with another object for equality.
     * Two AcademicYear objects are considered equal if their year identifiers are the same.
     *
     * @param o The object to compare with.
     * @return true if the given object is an instance of AcademicYear and has the same year identifier, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return yearIdentifier.equals(that.yearIdentifier);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(yearIdentifier);
    }
}