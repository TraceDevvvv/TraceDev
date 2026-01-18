package com.example.domain;

import java.util.Date;

/**
 * Represents an absence record for a student.
 */
public class Absence {
    private String id;
    private String studentId;
    private Date date;
    private AbsenceStatus status;
    private String reason;

    public Absence(String id, String studentId, Date date, AbsenceStatus status, String reason) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
        this.reason = reason;
    }

    public void modifyAbsence(AbsenceStatus status, String reason) {
        // In a real‑world scenario, we might keep the old status for auditing.
        // For simplicity, we just update the fields.
        this.status = status;
        this.reason = reason;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public AbsenceStatus getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }
}