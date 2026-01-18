package com.example.dto;

import com.example.model.Student;

/**
 * Data Transfer Object for student information including counts.
 */
public class StudentDTO {
    private String id;
    private String name;
    private String email;
    private int totalAbsences;
    private int totalNotes;

    public StudentDTO(Student student, int totalAbsences, int totalNotes) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.totalAbsences = totalAbsences;
        this.totalNotes = totalNotes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getTotalAbsences() {
        return totalAbsences;
    }

    public int getTotalNotes() {
        return totalNotes;
    }
}