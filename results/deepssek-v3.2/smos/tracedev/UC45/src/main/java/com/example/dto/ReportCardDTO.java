package com.example.dto;

import java.util.List;
import java.util.Map;

/**
 * DTO for Report Card information.
 */
public class ReportCardDTO {
    private String studentId;
    private String studentName;
    private List<String> months;
    private Map<String, GradeDTO> gradeData;
    private Map<String, Double> attendanceData;

    public ReportCardDTO() {}

    public ReportCardDTO(String studentId, String studentName, List<String> months,
                         Map<String, GradeDTO> gradeData, Map<String, Double> attendanceData) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.months = months;
        this.gradeData = gradeData;
        this.attendanceData = attendanceData;
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

    public List<String> getMonths() {
        return months;
    }

    public void setMonths(List<String> months) {
        this.months = months;
    }

    public Map<String, GradeDTO> getGradeData() {
        return gradeData;
    }

    public void setGradeData(Map<String, GradeDTO> gradeData) {
        this.gradeData = gradeData;
    }

    public Map<String, Double> getAttendanceData() {
        return attendanceData;
    }

    public void setAttendanceData(Map<String, Double> attendanceData) {
        this.attendanceData = attendanceData;
    }
}