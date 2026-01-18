package com.example.school;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for Disciplinary Note information.
 * Used for displaying disciplinary note details, including student name.
 */
public class DisciplinaryNoteDTO {
    private String studentName;
    private Date date;
    private String description;

    /**
     * Constructs a new DisciplinaryNoteDTO.
     * @param studentName The name of the student.
     * @param date The date the note was issued.
     * @param description A description of the disciplinary incident.
     */
    public DisciplinaryNoteDTO(String studentName, Date date, String description) {
        this.studentName = studentName;
        this.date = date;
        this.description = description;
    }

    /**
     * Gets the student's name.
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the date the note was issued.
     * @return The date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the description of the disciplinary incident.
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "  DisciplinaryNoteDTO{" +
               "studentName='" + studentName + '\'' +
               ", date=" + date +
               ", description='" + description + '\'' +
               '}';
    }
}