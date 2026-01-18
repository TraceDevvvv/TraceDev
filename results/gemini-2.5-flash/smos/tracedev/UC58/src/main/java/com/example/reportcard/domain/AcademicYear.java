package com.example.reportcard.domain;

/**
 * Domain entity representing an academic year.
 */
public class AcademicYear {
    private String id;
    private String year; // e.g., "2023-2024"

    public AcademicYear(String id, String year) {
        this.id = id;
        this.year = year;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }
}