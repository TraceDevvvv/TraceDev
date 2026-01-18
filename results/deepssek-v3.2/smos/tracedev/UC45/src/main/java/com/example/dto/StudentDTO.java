package com.example.dto;

/**
 * DTO for Student information.
 */
public class StudentDTO {
    private String studentId;
    private String name;

    public StudentDTO() {}

    public StudentDTO(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}