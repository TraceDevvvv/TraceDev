package com.example.dtos;

/**
 * Data Transfer Object for user input.
 */
public class UserInputDTO {
    private String studentId;
    private String justification;
    private String disciplinaryNote;

    public UserInputDTO(String studentId, String justification, String disciplinaryNote) {
        this.studentId = studentId;
        this.justification = justification;
        this.disciplinaryNote = disciplinaryNote;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getJustification() {
        return justification;
    }

    public String getDisciplinaryNote() {
        return disciplinaryNote;
    }
}