package com.example.school;

import java.util.Date;

/**
 * Represents a Delay record for a student in a class.
 */
public class Delay {
    private String delayId;
    private String studentId;
    private String classId;
    private Date date;
    private int durationMinutes;
    private String justification;

    /**
     * Constructs a new Delay record.
     * @param delayId The unique identifier for the delay.
     * @param studentId The ID of the student.
     * @param classId The ID of the class where the delay occurred.
     * @param date The date of the delay.
     * @param durationMinutes The duration of the delay in minutes.
     * @param justification A brief explanation for the delay.
     */
    public Delay(String delayId, String studentId, String classId, Date date, int durationMinutes, String justification) {
        this.delayId = delayId;
        this.studentId = studentId;
        this.classId = classId;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.justification = justification;
    }

    /**
     * Gets the unique identifier of the delay record.
     * @return The delay ID.
     */
    public String getDelayId() {
        return delayId;
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
     * Gets the date of the delay.
     * @return The date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the duration of the delay in minutes.
     * @return The duration in minutes.
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * Gets the justification for the delay.
     * @return The justification.
     */
    public String getJustification() {
        return justification;
    }
}