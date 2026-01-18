package com.example.justification.domain.model;

/**
 * Represents a Student in the domain model.
 */
public class Student {
    private String studentId;
    private String name;

    /**
     * Constructs a new Student.
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     */
    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    /**
     * Gets the student's ID.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
}