package com.example.exception;

/**
 * Custom exception for when a student is not found.
 * Satisfies requirement REQ-ERROR-001.
 */
public class StudentNotFoundException extends RuntimeException {
    private String studentId;
    
    public StudentNotFoundException(String studentId) {
        super("Student not found: " + studentId);
        this.studentId = studentId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    @Override
    public String getMessage() {
        return "Student with ID " + studentId + " was not found in the system.";
    }
}