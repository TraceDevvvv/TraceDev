package com.example.model;

import java.util.Date;

/**
 * Represents a justification for absence or delay.
 */
public class Justification {
    private String studentName;
    private String justificationType;
    private Date date;

    public Justification(String studentName, String justificationType, Date date) {
        this.studentName = studentName;
        this.justificationType = justificationType;
        this.date = date;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getJustificationType() {
        return justificationType;
    }

    public void setJustificationType(String justificationType) {
        this.justificationType = justificationType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}