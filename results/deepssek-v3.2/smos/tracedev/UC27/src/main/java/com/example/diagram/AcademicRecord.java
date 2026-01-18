package com.example.diagram;

import java.util.Date;

/**
 * Entity representing an academic record within a register.
 */
public class AcademicRecord {
    private String recordId;
    private String studentId;
    private String grade;
    private Date date;

    public AcademicRecord(String recordId, String studentId, String grade, Date date) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.grade = grade;
        this.date = date;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}