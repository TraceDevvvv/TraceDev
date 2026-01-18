package com.example.reportcard.domain;

import java.util.Map;
import java.util.HashMap;

/**
 * Domain entity representing a student's report card.
 */
public class ReportCard {
    private String studentId;
    private Map<String, Integer> subjectGrades;

    public ReportCard(String studentId, Map<String, Integer> subjectGrades) {
        this.studentId = studentId;
        this.subjectGrades = subjectGrades != null ? new HashMap<>(subjectGrades) : new HashMap<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public Map<String, Integer> getSubjectGrades() {
        return new HashMap<>(subjectGrades);
    }

    /**
     * Modifies the grade for a given subject.
     */
    public void modifyGrade(String subject, Integer newGrade) {
        subjectGrades.put(subject, newGrade);
    }

    /**
     * Validates the report card data (e.g., grades within 0–100).
     */
    public Boolean validate() {
        for (int grade : subjectGrades.values()) {
            if (grade < 0 || grade > 100) {
                return false;
            }
        }
        return true;
    }
}