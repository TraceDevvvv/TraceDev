package com.example.model;

import java.util.Date;

/**
 * Represents a student's absence.
 */
public class Absence {
    private String studentName;
    private Date date;
    private String reason;

    public Absence(String studentName, Date date, String reason) {
        this.studentName = studentName;
        this.date = date;
        this.reason = reason;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}