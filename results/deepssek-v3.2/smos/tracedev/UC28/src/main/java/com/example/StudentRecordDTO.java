package com.example;

/**
 * Data Transfer Object for StudentRecord.
 */
public class StudentRecordDTO {
    public String studentId;
    public StudentStatus status;

    public StudentRecordDTO(String studentId, StudentStatus status) {
        this.studentId = studentId;
        this.status = status;
    }
}