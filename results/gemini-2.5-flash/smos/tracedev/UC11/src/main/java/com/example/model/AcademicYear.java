package com.example.model;

/**
 * Represents an academic year.
 */
public class AcademicYear {
    private String id;
    private int year;
    private String description;

    public AcademicYear(String id, int year, String description) {
        this.id = id;
        this.year = year;
        this.description = description;
    }

    // Getters
    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
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