package com.example.smos.domain;

/**
 * Represents a class or course offered in a specific academic year.
 */
public class Class {
    private String id;
    private String name;
    private String academicYearId;
    private String description;

    /**
     * Constructs a new Class.
     * @param id The unique identifier for the class.
     * @param name The name of the class.
     * @param academicYearId The ID of the academic year this class belongs to.
     * @param description A brief description of the class.
     */
    public Class(String id, String name, String academicYearId, String description) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
        this.description = description;
    }

    /**
     * Gets the name of the class.
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the academic year this class belongs to.
     * @return The academic year ID.
     */
    public String getAcademicYearId() {
        return academicYearId;
    }

    /**
     * Gets the description of the class.
     * @return The class description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the unique identifier for this class.
     * @return The class ID.
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Class{id='" + id + "', name='" + name + "', academicYearId='" + academicYearId + "', description='" + description + "'}";
    }
}