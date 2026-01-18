package com.example.school.domain;

/**
 * Represents a class (e.g., Grade 1A, Grade 12B) in the school system.
 */
public class Class {
    public String id;
    public String name;
    public String academicYearId;

    /**
     * Constructs a new Class.
     * @param id The unique identifier for the class.
     * @param name The name of the class.
     * @param academicYearId The ID of the academic year this class belongs to.
     */
    public Class(String id, String name, String academicYearId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }
}