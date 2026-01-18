package com.schoolsystem.domain;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a student's report card.
 */
public class ReportCard {
    private String reportId;
    private String studentId;
    private String term;
    private int year;
    private Map<String, String> grades; // subject -> grade

    public ReportCard(String reportId, String studentId, String term, int year, Map<String, String> grades) {
        this.reportId = reportId;
        this.studentId = studentId;
        this.term = term;
        this.year = year;
        this.grades = grades;
    }

    public String getReportId() {
        return reportId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    /**
     * Calculates overall performance based on grades.
     * For simplicity, we assume it returns a descriptive string.
     */
    public String getOverallPerformance() {
        if (grades == null || grades.isEmpty()) {
            return "No grades available";
        }
        // Simple average logic: assume grades are numeric strings
        double sum = 0;
        int count = 0;
        for (String grade : grades.values()) {
            try {
                sum += Double.parseDouble(grade);
                count++;
            } catch (NumberFormatException e) {
                // ignore non-numeric grades
            }
        }
        if (count == 0) return "Not applicable";
        double avg = sum / count;
        if (avg >= 90) return "Excellent";
        if (avg >= 80) return "Good";
        if (avg >= 70) return "Average";
        return "Needs Improvement";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return Objects.equals(reportId, that.reportId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reportId);
    }
}