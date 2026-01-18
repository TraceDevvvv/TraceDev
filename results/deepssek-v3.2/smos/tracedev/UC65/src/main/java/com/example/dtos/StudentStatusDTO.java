package com.example.dtos;

/**
 * Data Transfer Object for student status.
 */
public class StudentStatusDTO {
    private String studentId;
    private String status;
    private Boolean isDelayed;
    private String justification;
    private String disciplinaryNote;

    public StudentStatusDTO(String studentId, String status, Boolean isDelayed, String justification, String disciplinaryNote) {
        this.studentId = studentId;
        this.status = status;
        this.isDelayed = isDelayed;
        this.justification = justification;
        this.disciplinaryNote = disciplinaryNote;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStatus() {
        return status;
    }

    public Boolean isDelayed() {
        return isDelayed;
    }

    public String getJustification() {
        return justification;
    }

    public String getDisciplinaryNote() {
        return disciplinaryNote;
    }
}