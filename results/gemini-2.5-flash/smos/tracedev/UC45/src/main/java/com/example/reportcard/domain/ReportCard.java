package com.example.reportcard.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a student's report card for a specific academic period.
 */
public class ReportCard {
    public String id;
    public Student student;
    public AcademicYear academicYear;
    public List<Month> periodMonths;
    public Map<String, String> grades; // e.g., "Subject" -> "Grade"

    /**
     * Constructs a new ReportCard.
     *
     * @param id The unique identifier for the report card.
     * @param student The student for whom the report card is generated.
     * @param academicYear The academic year the report card belongs to.
     * @param periodMonths The list of months covered by this report card.
     * @param grades A map of subjects to their respective grades.
     */
    public ReportCard(String id, Student student, AcademicYear academicYear, List<Month> periodMonths, Map<String, String> grades) {
        this.id = id;
        this.student = student;
        this.academicYear = academicYear;
        this.periodMonths = periodMonths;
        this.grades = grades;
    }

    // Getters
    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public List<Month> getPeriodMonths() {
        return periodMonths;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    /**
     * Generates a detailed string representation of the report card.
     *
     * @return A formatted string with report card details.
     */
    public String getReportDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Report Card ---\n");
        sb.append("Report ID: ").append(id).append("\n");
        sb.append("Student: ").append(student.getName()).append(" (ID: ").append(student.getId()).append(")\n");
        sb.append("Academic Year: ").append(academicYear.getYear()).append(" (ID: ").append(academicYear.getId()).append(")\n");
        sb.append("Period: ");
        if (periodMonths != null && !periodMonths.isEmpty()) {
            sb.append(periodMonths.stream().map(Month::getName).collect(Collectors.joining(", ")));
        } else {
            sb.append("N/A");
        }
        sb.append("\nGrades:\n");
        if (grades != null && !grades.isEmpty()) {
            grades.forEach((subject, grade) -> sb.append("  - ").append(subject).append(": ").append(grade).append("\n"));
        } else {
            sb.append("  No grades available.\n");
        }
        sb.append("-------------------\n");
        return sb.toString();
    }
}