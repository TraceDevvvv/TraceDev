package com.example.digitalregister.model;

/**
 * Represents an academic class (e.g., Grade 5A).
 */
public class AcademicClass {
    private String id;
    private String name;
    private int grade;

    /**
     * Constructs a new AcademicClass instance.
     * @param id A unique identifier for the academic class.
     * @param name The name of the class (e.g., "5th Grade A").
     * @param grade The grade level (e.g., 5).
     */
    public AcademicClass(String id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    /**
     * Gets the unique identifier for the academic class.
     * @return The academic class ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the academic class.
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the grade level of the academic class.
     * @return The grade level.
     */
    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "AcademicClass{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", grade=" + grade +
               '}';
    }
}