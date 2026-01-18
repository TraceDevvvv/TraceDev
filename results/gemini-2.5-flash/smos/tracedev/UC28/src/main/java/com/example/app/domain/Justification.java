package com.example.app.domain;

import com.example.app.Domain;
import java.util.Date;

/**
 * Represents a justification for a student's absence.
 */
public class Justification implements Domain {
    public String id;
    public String studentId;
    public String registerId;
    public Date date;
    public String reason;
    public JustificationStatus status;


    public Justification(String id, String studentId, String registerId, Date date, String reason, JustificationStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.registerId = registerId;
        this.date = date;
        this.reason = reason;
        this.status = status;
    }

    // Getters and setters for public fields
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public Date getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public JustificationStatus getStatus() {
        return status;
    }

    public void setStatus(JustificationStatus status) {
        this.status = status;
    }
}