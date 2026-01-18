package com.example.schoolreports;

import java.util.Date;

/**
 * Represents a summary of a student's report, providing
 * basic information for a list view. This is a domain model class.
 */
public class StudentReportSummary {
    private String id; // Unique identifier for the report summary
    private String studentName;
    private Date lastReportDate;

    /**
     * Constructs a new StudentReportSummary instance.
     *
     * @param id The unique ID of the report summary.
     * @param studentName The name of the student.
     * @param lastReportDate The date of the last report for this student.
     */
    public StudentReportSummary(String id, String studentName, Date lastReportDate) {
        this.id = id;
        this.studentName = studentName;
        this.lastReportDate = lastReportDate;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public Date getLastReportDate() {
        return lastReportDate;
    }

    @Override
    public String toString() {
        return "Report ID: " + id + ", Student: " + studentName + ", Last Report: " + lastReportDate;
    }
}