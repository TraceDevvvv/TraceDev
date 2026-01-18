package com.example.attendancetracker.model;

/**
 * Represents a student in the system.
 */
public class Student {
    private String studentId;
    public String studentName;
    private String parentId; // Association to Parent by ID

    public Student(String studentId, String studentName, String parentId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.parentId = parentId;
    }

    // Getters for private fields
    public String getStudentId() {
        return studentId;
    }

    public String getParentId() {
        return parentId;
    }

    // Setters if needed, for simplicity we'll assume these are set at creation
    // public void setStudentName(String studentName) { this.studentName = studentName; }
}