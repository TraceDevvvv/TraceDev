package com.example;

import java.util.Date;

/**
 * Represents a justification for a student's absence or lateness.
 */
public class Justification {
    private String justificationId;
    private String studentId;
    private Date recordDate;
    private String reason;

    public Justification(String justificationId, String studentId, Date recordDate, String reason) {
        this.justificationId = justificationId;
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.reason = reason;
    }

    public String getJustificationId() {
        return justificationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public String getReason() {
        return reason;
    }

    public void createJustification(String studentId, Date recordDate, String reason) {
        this.studentId = studentId;
        this.recordDate = recordDate;
        this.reason = reason;
        // Additional logic to persist justification could be added here.
    }

    public void updateJustification(String reason) {
        this.reason = reason;
        // Additional logic to update justification could be added here.
    }
}