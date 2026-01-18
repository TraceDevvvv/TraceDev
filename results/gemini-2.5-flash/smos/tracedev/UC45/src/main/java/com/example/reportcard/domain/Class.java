package com.example.reportcard.domain;

/**
 * Represents a class (e.g., 10A) in the domain model.
 */
public class Class {
    public String id;
    public String name;
    public String academicYearId; // Foreign key to AcademicYear

    /**
     * Constructs a new Class.
     *
     * @param id The unique identifier for the class.
     * @param name The name of the class (e.g., "10A").
     * @param academicYearId The ID of the academic year this class belongs to.
     */
    public Class(String id, String name, String academicYearId) {
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

    @Override
    public String toString() {
        return "Class{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}