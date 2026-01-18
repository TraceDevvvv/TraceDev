package com.example.dto;

import com.example.entity.Student;

/**
 * Data Transfer Object for student information.
 */
public class StudentDTO {
    public String studentId;
    public String name;
    public String parentEmail;

    public StudentDTO() {}

    public StudentDTO(String studentId, String name, String parentEmail) {
        this.studentId = studentId;
        this.name = name;
        this.parentEmail = parentEmail;
    }

    /**
     * Converts this DTO to a Student entity.
     */
    public Student toEntity() {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setName(this.name);
        // parentEmail would be set in ParentContact, not directly in Student.
        // For simplicity, we skip that mapping here.
        return student;
    }
}