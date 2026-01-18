package com.example.school;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for Delay information.
 * Used for displaying delay details, including student name.
 */
public class DelayDTO {
    private String studentName;
    private Date date;
    private int durationMinutes;
    private String justification;

    /**
     * Constructs a new DelayDTO.
     * @param studentName The name of the student.
     * @param date The date of the delay.
     * @param durationMinutes The duration of the delay in minutes.
     * @param justification A brief explanation for the delay.
     */
    public DelayDTO(String studentName, Date date, int durationMinutes, String justification) {
        this.studentName = studentName;
        this.date = date;
        this.durationMinutes = durationMinutes;
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

    @Override
    public String toString() {
        return "  DelayDTO{" +
               "studentName='" + studentName + '\'' +
               ", date=" + date +
               ", durationMinutes=" + durationMinutes +
               ", justification='" + justification + '\'' +
               '}';
    }
}