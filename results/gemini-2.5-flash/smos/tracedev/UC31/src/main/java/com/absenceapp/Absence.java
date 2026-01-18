package com.absenceapp;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents an absence record for a student.
 */
public class Absence {
    private String id;
    private String studentId;
    private Date date;
    private AbsenceType type;
    private String reason;
    private AbsenceStatus status;

    public Absence(String studentId, Date date, AbsenceType type, String reason, AbsenceStatus status) {
        this.id = UUID.randomUUID().toString(); // Generate unique ID for new absence
        this.studentId = studentId;
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.status = status;
    }

    // Constructor for existing absence records (e.g., loaded from repo)
    public Absence(String id, String studentId, Date date, AbsenceType type, String reason, AbsenceStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.type = type;
        this.reason = reason;
        this.status = status;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getDate() {
        return date;
    }

    public AbsenceType getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

    public AbsenceStatus getStatus() {
        return status;
    }

    // Setters (for internal updates)
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Updates the date, type, and reason of the absence.
     *
     * @param date The new date of the absence.
     * @param type The new type of the absence.
     * @param reason The new reason for the absence.
     */
    public void update(Date date, AbsenceType type, String reason) {
        this.date = date;
        this.type = type;
        this.reason = reason;
    }

    /**
     * Sets the status of the absence.
     *
     * @param status The new status of the absence.
     */
    public void setStatus(AbsenceStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return Objects.equals(id, absence.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Absence{" +
                "id='" + id + '\'' +
                ", studentId='" + studentId + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                '}';
    }
}