package com.example.reportcard.dto;

import java.util.Map;

/**
 * DTO for transferring Report Card information.
 * Used for displaying a student's report card.
 */
public class ReportCardDTO {
    private String studentName;
    private String academicYear;
    private String quarter;
    private Map<String, String> grades; // e.g., "Math" -> "A", "Science" -> "B+"

    public ReportCardDTO(String studentName, String academicYear, String quarter, Map<String, String> grades) {
        this.studentName = studentName;
        this.academicYear = academicYear;
        this.quarter = quarter;
        this.grades = grades;
    }

    // Getters
    public String getStudentName() {
        return studentName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getQuarter() {
        return quarter;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    // Setters (if needed)
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public void setGrades(Map<String, String> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "ReportCardDTO{" +
               "studentName='" + studentName + '\'' +
               ", academicYear='" + academicYear + '\'' +
               ", quarter='" + quarter + '\'' +
               ", grades=" + grades +
               '}';
    }
}