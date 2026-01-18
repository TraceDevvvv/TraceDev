package com.example;

import java.util.Objects;

/**
 * Represents an academic year entity.
 */
public class AcademicYear {
    private String id;
    private String year;

    // Default constructor for potential JSON deserialization
    public AcademicYear() {
    }

    public AcademicYear(String id, String year) {
        this.id = id;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "AcademicYear{" +
                "id='" + id + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return Objects.equals(id, that.id); // Assuming ID is unique
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}