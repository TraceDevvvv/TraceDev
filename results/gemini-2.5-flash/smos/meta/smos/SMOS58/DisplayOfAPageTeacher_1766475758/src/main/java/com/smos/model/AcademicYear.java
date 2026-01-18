package com.smos.model;

import java.util.Objects;

/**
 * Represents an academic year in the school system.
 * An academic year is typically identified by a unique string, e.g., "2023-2024".
 */
public class AcademicYear {
    private final String year;

    /**
     * Constructs a new AcademicYear with the specified year string.
     *
     * @param year The string representation of the academic year (e.g., "2023-2024").
     * @throws IllegalArgumentException if the year string is null or empty.
     */
    public AcademicYear(String year) {
        if (year == null || year.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty.");
        }
        this.year = year;
    }

    /**
     * Returns the string representation of the academic year.
     *
     * @return The academic year string.
     */
    public String getYear() {
        return year;
    }

    /**
     * Compares this AcademicYear object to the specified object.
     * The result is true if and only if the argument is not null and is an AcademicYear object
     * that represents the same academic year as this object.
     *
     * @param o The object to compare this AcademicYear against.
     * @return true if the given object represents an AcademicYear equivalent to this AcademicYear, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(year, that.year);
    }

    /**
     * Returns a hash code for this AcademicYear object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    /**
     * Returns a string representation of this AcademicYear object.
     *
     * @return A string representation of the academic year.
     */
    @Override
    public String toString() {
        return "AcademicYear{" +
               "year='" + year + '\'' +
               '}';
    }
}