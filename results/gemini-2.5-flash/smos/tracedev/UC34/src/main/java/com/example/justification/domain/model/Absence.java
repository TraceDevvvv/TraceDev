package com.example.justification.domain.model;

import java.util.Date;

/**
 * Represents an Absence record in the domain model.
 */
public class Absence {
    private String absenceId;
    private String studentId;
    private Date date;
    private String reason;
    private boolean isJustified;
    private String justificationDetails;

    /**
     * Constructs a new Absence.
     *
     * @param id The unique identifier for the absence.
     * @param studentId The ID of the student associated with the absence.
     * @param date The date of the absence.
     * @param reason The reason for the absence.
     * @param isJustified Boolean indicating if the absence is justified.
     * @param details Additional details about the justification.
     */
    public Absence(String id, String studentId, Date date, String reason, boolean isJustified, String details) {
        this.absenceId = id;
        this.studentId = studentId;
        this.date = date;
        this.reason = reason;
        this.isJustified = isJustified;
        this.justificationDetails = details;
    }

    /**
     * Gets the absence ID.
     * @return The absence ID.
     */
    public String getAbsenceId() {
        return absenceId;
    }

    /**
     * Gets the student ID associated with this absence.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the date of the absence.
     * @return The date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the reason for the absence.
     * @return The reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Checks if the absence is justified.
     * @return True if justified, false otherwise.
     */
    public boolean isJustified() {
        return isJustified;
    }

    /**
     * Gets the justification details.
     * @return The justification details.
     */
    public String getJustificationDetails() {
        return justificationDetails;
    }

    /**
     * Marks the absence as justified with provided details.
     * @param details The justification details.
     */
    public void markAsJustified(String details) {
        this.isJustified = true;
        this.justificationDetails = details;
    }
}