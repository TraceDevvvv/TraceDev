package com.example.reportcard.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for report card data.
 */
public class ReportCardDTO {
    private String studentId;
    private Map<String, Integer> subjectGrades;

    public ReportCardDTO(String studentId, Map<String, Integer> subjectGrades) {
        this.studentId = studentId;
        this.subjectGrades = subjectGrades != null ? new HashMap<>(subjectGrades) : new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Map<String, Integer> getSubjectGrades() {
        return new HashMap<>(subjectGrades);
    }

    public void setSubjectGrades(Map<String, Integer> subjectGrades) {
        this.subjectGrades = subjectGrades != null ? new HashMap<>(subjectGrades) : new HashMap<>();
    }
    
    public static ReportCardDTO convertToDTO(ReportCard reportCard) {
        return new ReportCardDTO(reportCard.getStudentId(), reportCard.getSubjectGrades());
    }
}