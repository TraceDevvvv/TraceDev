package com.example.school;

/**
 * Represents a Professor entity in the system.
 */
public class Professor {
    private String professorId;
    private String name;

    /**
     * Constructs a new Professor with the specified ID and name.
     * @param professorId The unique identifier for the professor.
     * @param name The name of the professor.
     */
    public Professor(String professorId, String name) {
        this.professorId = professorId;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the professor.
     * @return The professor's ID.
     */
    public String getProfessorId() {
        return professorId;
    }

    /**
     * Gets the name of the professor.
     * @return The professor's name.
     */
    public String getName() {
        return name;
    }
}