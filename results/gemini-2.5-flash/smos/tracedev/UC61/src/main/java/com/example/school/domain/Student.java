package com.example.school.domain;

/**
 * Represents a Student in the domain model.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentId; // Association with Parent

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The name of the student.
     * @param parentId The unique identifier of the parent associated with this student.
     */
    public Student(String studentId, String name, String parentId) {
        this.studentId = studentId;
        this.name = name;
        this.parentId = parentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }
}