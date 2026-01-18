package com.example.dto;

import java.util.Date;

/**
 * Represents an absence record for a student.
 */
public class AbsenceRecord {
    private Date date;
    private String reason;
    private boolean isJustified;

    public AbsenceRecord() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isJustified() {
        return isJustified;
    }

    public void setJustified(boolean justified) {
        isJustified = justified;
    }
}