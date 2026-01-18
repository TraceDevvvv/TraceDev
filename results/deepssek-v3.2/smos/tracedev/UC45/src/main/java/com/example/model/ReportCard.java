package com.example.model;

import java.util.List;
import java.util.Map;

/**
 * Entity representing a report card.
 */
public class ReportCard {
    private String studentId;
    private String studentName;
    private String academicYear;
    private List<String> months;
    private Map<String, Grade> grades;
    private Map<String, Double> attendance;

    public ReportCard() {}

    public ReportCard(String studentId, String studentName, String academicYear, List<String> months,
                      Map<String, Grade> grades, Map<String, Double> attendance) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.academicYear = academicYear;
        this.months = months;
        this.grades = grades;
        this.attendance = attendance;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public Map<String, Grade> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Grade> grades) {
        this.grades = grades;
    }

    public Map<String, Double> getAttendance() {
        return attendance;
    }

    public void setAttendance(Map<String, Double> attendance) {
        this.attendance = attendance;
    }
}