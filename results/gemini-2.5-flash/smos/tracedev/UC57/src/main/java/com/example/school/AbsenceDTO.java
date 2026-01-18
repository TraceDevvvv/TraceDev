package com.example.school;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for Absence information.
 * Used for displaying absence details, including student name.
 */
public class AbsenceDTO {
    private String studentName;
    private Date date;
    private String type;
    private String justification;

    /**
     * Constructs a new AbsenceDTO.
     * @param studentName The name of the student.
     * @param date The date of the absence.
     * @param type The type of absence (e.g., "Excused", "Unexcused").
     * @param justification A brief explanation for the absence.
     */
    public AbsenceDTO(String studentName, Date date, String type, String justification) {
        this.studentName = studentName;
        this.date = date;
        this.type = type;
        this.justification = justification;
    }

    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the date of the absence.
     * @return The date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the type of absence.
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the justification for the absence.
     * @return The justification.
     */
    public String getJustification() {
        return justification;
    }

    @Override
    public String toString() {
        return "  AbsenceDTO{" +
               "studentName='" + studentName + '\'' +
               ", date=" + date +
               ", type='" + type + '\'' +
               ", justification='" + justification + '\'' +
               '}';
    }
}