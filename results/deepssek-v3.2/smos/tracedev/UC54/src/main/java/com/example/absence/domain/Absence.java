package com.example.absence.domain;

import java.util.Date;

/**
 * Represents an Absence record for a student in a class on a specific date.
 */
public class Absence {
    private String absenceId;
    private Student student;
    private Class classRecord;
    private Date date;
    private String recordedBy;
    private boolean absent;

    public Absence(Student student, Class classRecord, Date date, String recordedBy) {
        this.student = student;
        this.classRecord = classRecord;
        this.date = date;
        this.recordedBy = recordedBy;
        this.absent = false; // default to present
    }

    public void markAbsent() {
        this.absent = true;
    }

    public void markPresent() {
        this.absent = false;
    }

    public boolean isAbsent() {
        return absent;
    }

    public Student getStudent() {
        return student;
    }

    public Date getDate() {
        return date;
    }

    // Additional getters for potential use
    public String getAbsenceId() {
        return absenceId;
    }

    public void setAbsenceId(String absenceId) {
        this.absenceId = absenceId;
    }

    public Class getClassRecord() {
        return classRecord;
    }

    public String getRecordedBy() {
        return recordedBy;
    }
}