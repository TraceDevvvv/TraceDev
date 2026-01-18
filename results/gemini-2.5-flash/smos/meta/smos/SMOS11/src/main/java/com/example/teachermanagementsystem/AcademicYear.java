package com.example.teachermanagementsystem;

import java.util.Objects;

/**
 * Represents an academic year in the system.
 * Each academic year has a unique ID and a descriptive name (e.g., "2023-2024").
 */
public class AcademicYear {
    private String id;
    private String yearName;

    /**
     * Constructs a new AcademicYear.
     * @param id A unique identifier for the academic year.
     * @param yearName A descriptive name for the academic year (e.g., "2023-2024").
     */
    public AcademicYear(String id, String yearName) {
        this.id = id;
        this.yearName = yearName;
    }

    /**
     * Returns the unique ID of the academic year.
     * @return The academic year's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the descriptive name of the academic year.
     * @return The academic year's name.
     */
    public String getYearName() {
        return yearName;
    }

    /**
     * Provides a string representation of the AcademicYear, primarily its name.
     * @return The year name.
     */
    @Override
    public String toString() {
        return yearName;
    }

    /**
     * Compares this AcademicYear to the specified object. The result is true if and only if
     * the argument is not null and is an AcademicYear object that has the same ID as this object.
     * @param o The object to compare this AcademicYear against.
     * @return true if the given object represents an AcademicYear equivalent to this academic year, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}