package com.example.smos.dto;

/**
 * Data Transfer Object for carrying student monitoring information.
 * This class corresponds to the 'StudentMonitoringDTO' class in the UML diagram.
 */
public class StudentMonitoringDTO {
    private String studentId;
    private String studentName;
    private int absencesCount;
    private int notesCount;

    /**
     * Constructs a new StudentMonitoringDTO.
     *
     * @param id The student's ID.
     * @param name The student's name.
     * @param absences The number of absences.
     * @param notes The number of notes.
     */
    public StudentMonitoringDTO(String id, String name, int absences, int notes) {
        this.studentId = id;
        this.studentName = name;
        this.absencesCount = absences;
        this.notesCount = notes;
    }

    /**
     * Gets the student's ID.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the number of absences.
     * @return The absences count.
     */
    public int getAbsencesCount() {
        return absencesCount;
    }

    /**
     * Gets the number of notes.
     * @return The notes count.
     */
    public int getNotesCount() {
        return notesCount;
    }

    /**
     * Provides a string representation of the DTO for display purposes.
     * @return A formatted string with student monitoring details.
     */
    @Override
    public String toString() {
        return "StudentMonitoringDTO{" +
               "studentId='" + studentId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", absencesCount=" + absencesCount +
               ", notesCount=" + notesCount +
               '}';
    }
}