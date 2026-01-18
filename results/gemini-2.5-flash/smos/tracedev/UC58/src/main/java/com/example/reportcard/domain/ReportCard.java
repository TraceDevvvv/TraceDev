package com.example.reportcard.domain;

import java.util.Map;

/**
 * Domain entity representing a student's report card for a specific quarter.
 */
public class ReportCard {
    private String id;
    private String studentId;
    private String academicYearId; // Added back for data consistency in repository lookup.
    private String quarter; // e.g., "Q1", "Q2", "Q3", "Q4"
    private Map<String, String> grades; // e.g., "Math" -> "A", "Science" -> "B+"

    public ReportCard(String id, String studentId, String academicYearId, String quarter, Map<String, String> grades) {
        this.id = id;
        this.studentId = studentId;
        this.academicYearId = academicYearId;
        this.quarter = quarter;
        this.grades = grades;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYearId() {
        return academicYearId;
    }

    public String getQuarter() {
        return quarter;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setAcademicYearId(String academicYearId) {
        this.academicYearId = academicYearId;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setGrades(Map<String, String> grades) {
        this.grades = grades;
    }
}