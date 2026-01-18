package com.example.dto;

import java.util.List;
import java.util.Objects;

/**
 * Data Transfer Object for report card information.
 * Used to transfer data between layers, especially to the view.
 */
public class ReportCardDTO {
    private final String studentName;
    private final String className;
    private final String semesterName;
    private final List<GradeDTO> grades;
    private final double averageGrade;

    public ReportCardDTO(String studentName, String className, String semesterName, List<GradeDTO> grades, double averageGrade) {
        this.studentName = Objects.requireNonNull(studentName);
        this.className = Objects.requireNonNull(className);
        this.semesterName = Objects.requireNonNull(semesterName);
        this.grades = Objects.requireNonNull(grades);
        this.averageGrade = averageGrade;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getClassName() {
        return className;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public List<GradeDTO> getGrades() {
        return grades;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * Creates a formatted string representation of the entire report.
     * The format is a simple text representation; in a real application
     * this might produce HTML or a PDF.
     * @return a formatted report string
     */
    public String formattedReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Card\n");
        sb.append("===========\n");
        sb.append("Student: ").append(studentName).append("\n");
        sb.append("Class: ").append(className).append("\n");
        sb.append("Semester: ").append(semesterName).append("\n");
        sb.append("\nGrades:\n");
        for (GradeDTO g : grades) {
            sb.append("  - ").append(g.getSubject()).append(": ").append(g.getGrade()).append("\n");
        }
        sb.append("\nAverage Grade: ").append(String.format("%.2f", averageGrade)).append("\n");
        return sb.toString();
    }

    /**
     * Validates that the report card contains all necessary data.
     * This method ensures the report is accurate and complete, as per the quality requirement.
     * @return true if the report is complete, false otherwise
     */
    public boolean validateCompleteness() {
        if (studentName == null || studentName.trim().isEmpty()) return false;
        if (className == null || className.trim().isEmpty()) return false;
        if (semesterName == null || semesterName.trim().isEmpty()) return false;
        if (grades == null || grades.isEmpty()) return false;
        for (GradeDTO g : grades) {
            if (!g.validate()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReportCardDTO{" +
                "studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", averageGrade=" + averageGrade +
                '}';
    }
}