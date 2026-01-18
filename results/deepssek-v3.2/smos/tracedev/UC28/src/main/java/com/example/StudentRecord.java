package com.example;

/**
 * Represents a student's record for a specific date.
 */
public class StudentRecord {
    private String studentId;
    private StudentStatus status;

    public StudentRecord(String studentId, StudentStatus status) {
        this.studentId = studentId;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void updateStatus(StudentStatus status) {
        this.status = status;
    }
}