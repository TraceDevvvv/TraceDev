package com.reportcard.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents an academic year within the report card system.
 * Each academic year has a specific identifier (e.g., "2023-2024") and
 * contains a collection of school classes active during that year.
 */
public class AcademicYear {
    private String year;
    private List<SchoolClass> classes;

    /**
     * Constructs a new AcademicYear object.
     *
     * @param year The identifier for the academic year (e.g., "2023-2024").
     */
    public AcademicYear(String year) {
        if (year == null || year.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty.");
        }
        this.year = year;
        this.classes = new ArrayList<>(); // Initialize with an empty list
    }

    /**
     * Returns the identifier for the academic year.
     *
     * @return The academic year string.
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the identifier for the academic year.
     *
     * @param year The new academic year string.
     */
    public void setYear(String year) {
        if (year == null || year.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty.");
        }
        this.year = year;
    }

    /**
     * Returns an unmodifiable list of school classes associated with this academic year.
     *
     * @return An unmodifiable list of SchoolClass objects.
     */
    public List<SchoolClass> getClasses() {
        return Collections.unmodifiableList(classes); // Return an unmodifiable view to prevent external modification
    }

    /**
     * Adds a school class to this academic year.
     *
     * @param schoolClass The SchoolClass object to add.
     */
    public void addClass(SchoolClass schoolClass) {
        if (schoolClass == null) {
            throw new IllegalArgumentException("School class cannot be null.");
        }
        // Prevent adding the same class multiple times based on classId
        if (!this.classes.contains(schoolClass)) {
            this.classes.add(schoolClass);
        }
    }

    /**
     * Finds a school class in this academic year by its class ID.
     *
     * @param classId The ID of the class to find.
     * @return The SchoolClass object if found, otherwise null.
     */
    public SchoolClass getClassById(String classId) {
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty.");
        }
        for (SchoolClass schoolClass : classes) {
            if (schoolClass.getClassId().equals(classId)) {
                return schoolClass;
            }
        }
        return null;
    }

    /**
     * Overrides the equals method to compare AcademicYear objects based on their year.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(year, that.year);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this AcademicYear object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    /**
     * Returns a string representation of the AcademicYear object.
     *
     * @return A string containing the academic year and the number of classes.
     */
    @Override
    public String toString() {
        return "AcademicYear{" +
               "year='" + year + '\'' +
               ", classCount=" + classes.size() +
               '}';
    }
}