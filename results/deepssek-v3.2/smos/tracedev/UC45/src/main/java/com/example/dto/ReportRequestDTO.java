package com.example.dto;

import java.util.List;

/**
 * DTO for report request containing student, class, year and months.
 */
public class ReportRequestDTO {
    private String studentId;
    private String classId;
    private String academicYear;
    private List<String> months;

    public ReportRequestDTO() {}

    public ReportRequestDTO(String studentId, String classId, String academicYear, List<String> months) {
        this.studentId = studentId;
        this.classId = classId;
        this.academicYear = academicYear;
        this.months = months;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
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
}