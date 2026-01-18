package com.example.monitoring.application;

/**
 * Application Layer: Data Transfer Object (DTO) for student monitoring information.
 * Used to transfer student data between layers in a simplified and structured way.
 */
public class StudentMonitoringDTO {
    private String studentId;
    private String studentName;
    private int absences;
    private int notes;

    /**
     * Constructor for StudentMonitoringDTO.
     * @param studentId The unique identifier of the student.
     * @param studentName The name of the student.
     * @param absences The total number of absences for the student.
     * @param notes The total number of notes (e.g., behavioral incidents) for the student.
     */
    public StudentMonitoringDTO(String studentId, String studentName, int absences, int notes) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.absences = absences;
        this.notes = notes;
    }

    // Getters for all attributes

    /**
     * @return The unique identifier of the student.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @return The name of the student.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @return The total number of absences for the student.
     */
    public int getAbsences() {
        return absences;
    }

    /**
     * @return The total number of notes for the student.
     */
    public int getNotes() {
        return notes;
    }
}