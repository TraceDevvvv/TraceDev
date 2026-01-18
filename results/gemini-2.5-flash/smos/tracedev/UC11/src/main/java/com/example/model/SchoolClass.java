package com.example.model;

/**
 * Represents a class (e.g., Math 101, Grade 5 Section A) within an academic year.
 * Renamed from 'Class' to 'SchoolClass' to avoid Java keyword conflict.
 */
public class SchoolClass {
    private String id;
    private String name;
    private String academicYearId;

    public SchoolClass(String id, String name, String academicYearId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    // Setters (if needed)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", academicYearId='" + academicYearId + '\'' +
               '}';
    }
}