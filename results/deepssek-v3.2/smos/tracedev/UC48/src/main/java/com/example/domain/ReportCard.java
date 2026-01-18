package com.example.domain;

import java.util.Date;
import java.util.Map;

/**
 * Domain entity representing a report card.
 */
public class ReportCard {
    private String id;
    private String studentId;
    private String className;
    private Map<String, String> grades;
    private Date reportDate;

    public ReportCard(String id, String studentId, String className, Map<String, String> grades, Date reportDate) {
        this.id = id;
        this.studentId = studentId;
        this.className = className;
        this.grades = grades;
        this.reportDate = reportDate;
    }

    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getClassName() {
        return className;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public Date getReportDate() {
        return reportDate;
    }
}