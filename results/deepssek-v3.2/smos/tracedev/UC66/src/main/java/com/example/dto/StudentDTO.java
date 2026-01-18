package com.example.dto;

import java.util.Objects;

/**
 * Data Transfer Object for a student.
 */
public class StudentDTO {
    private final String studentId;
    private final String fullName;

    public StudentDTO(String studentId, String fullName) {
        this.studentId = Objects.requireNonNull(studentId);
        this.fullName = Objects.requireNonNull(fullName);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return fullName;
    }
}