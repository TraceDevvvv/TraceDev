package com.example.absence.domain;

/**
 * Represents a Student in the domain layer.
 */
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String parentEmail;

    public Student(String studentId, String firstName, String lastName, String parentEmail) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.parentEmail = parentEmail;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public boolean isEmailValid() {
        // Simple email validation: check for '@' character
        return parentEmail != null && parentEmail.contains("@");
    }

    /**
     * Finds a student by ID. In a real implementation, this would query a database.
     * For simplicity, we return a mock student.
     * @param studentId the ID of the student to find
     * @return the Student object, or null if not found
     */
    public static Student findById(String studentId) {
        // Mock implementation: return a sample student
        if ("S001".equals(studentId)) {
            return new Student("S001", "John", "Doe", "john.doe@example.com");
        } else if ("S002".equals(studentId)) {
            return new Student("S002", "Jane", "Smith", "jane.smith@example.com");
        } else {
            return null;
        }
    }
}