package com.example.disciplinarynote.domain;

/**
 * Represents a Student in the domain.
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
     * @return The studentId.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student's name.
     * @return The name of the student.
     */
    public String getName() {
        return name;
    }
}