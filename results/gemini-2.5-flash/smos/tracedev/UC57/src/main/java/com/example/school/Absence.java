package com.example.school;

import java.util.Date;

/**
 * Represents an Absence record for a student in a class.
 */
public class Absence {
    private String absenceId;
    private String studentId;
    private String classId;
    private Date date;
    private String type;
    private String justification;

    /**
     * Constructs a new Absence record.
     * @param absenceId The unique identifier for the absence.
     * @param studentId The ID of the student who was absent.
     * @param classId The ID of the class where the absence occurred.
     * @param date The date of the absence.
     * @param type The type of absence (e.g., "Excused", "Unexcused").
     * @param justification A brief explanation for the absence.
     */
    public Absence(String absenceId, String studentId, String classId, Date date, String type, String justification) {
        this.absenceId = absenceId;
        this.studentId = studentId;
        this.classId = classId;
        this.date = date;
        this.type = type;
        this.justification = justification;
    }

    /**
     * Gets the unique identifier of the absence record.
     * @return The absence ID.
     */
    public String getAbsenceId() {
        return absenceId;
    }

    /**
     * Gets the ID of the student.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the ID of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
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
}