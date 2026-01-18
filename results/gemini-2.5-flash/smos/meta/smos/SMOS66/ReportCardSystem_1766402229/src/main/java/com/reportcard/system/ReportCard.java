package com.reportcard.system;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a report card for a student for a specific academic year and quadrimestre.
 * It contains grades for various subjects.
 */
public class ReportCard {
    private String academicYear;
    private String quadrimestre;
    private Map<String, String> grades; // Subject name -> Grade (e.g., "Math" -> "A", "Physics" -> "85")

    /**
     * Constructs a new ReportCard object.
     *
     * @param academicYear The academic year this report card belongs to (e.g., "2023-2024").
     * @param quadrimestre The quadrimestre (semester/term) this report card covers (e.g., "Q1", "Q2", "Semester 1").
     */
    public ReportCard(String academicYear, String quadrimestre) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty.");
        }
        if (quadrimestre == null || quadrimestre.trim().isEmpty()) {
            throw new IllegalArgumentException("Quadrimestre cannot be null or empty.");
        }
        this.academicYear = academicYear;
        this.quadrimestre = quadrimestre;
        this.grades = new HashMap<>(); // Initialize with an empty map for grades
    }

    /**
     * Returns the academic year of this report card.
     *
     * @return The academic year.
     */
    public String getAcademicYear() {
        return academicYear;
    }

    /**
     * Sets the academic year of this report card.
     *
     * @param academicYear The new academic year.
     */
    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty.");
        }
        this.academicYear = academicYear;
    }

    /**
     * Returns the quadrimestre of this report card.
     *
     * @return The quadrimestre.
     */
    public String getQuadrimestre() {
        return quadrimestre;
    }

    /**
     * Sets the quadrimestre of this report card.
     *
     * @param quadrimestre The new quadrimestre.
     */
    public void setQuadrimestre(String quadrimestre) {
        if (quadrimestre == null || quadrimestre.trim().isEmpty()) {
            throw new IllegalArgumentException("Quadrimestre cannot be null or empty.");
        }
        this.quadrimestre = quadrimestre;
    }

    /**
     * Returns an unmodifiable map of grades for this report card.
     *
     * @return An unmodifiable map where keys are subject names and values are grades.
     */
    public Map<String, String> getGrades() {
        return Collections.unmodifiableMap(grades); // Return an unmodifiable view to prevent external modification
    }

    /**
     * Adds or updates a grade for a specific subject.
     *
     * @param subject The name of the subject (e.g., "Mathematics", "History").
     * @param grade The grade received for the subject (e.g., "A", "B+", "85").
     */
    public void addOrUpdateGrade(String subject, String grade) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        if (grade == null || grade.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade cannot be null or empty.");
        }
        this.grades.put(subject, grade);
    }

    /**
     * Retrieves the grade for a specific subject.
     *
     * @param subject The name of the subject.
     * @return The grade for the subject, or null if the subject is not found.
     */
    public String getGrade(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        return grades.get(subject);
    }

    /**
     * Overrides the equals method to compare ReportCard objects based on their academic year and quadrimestre.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return Objects.equals(academicYear, that.academicYear) &&
               Objects.equals(quadrimestre, that.quadrimestre);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this ReportCard object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(academicYear, quadrimestre);
    }

    /**
     * Returns a string representation of the ReportCard object, including academic year, quadrimestre, and grades.
     *
     * @return A string containing the report card details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReportCard for Academic Year: ").append(academicYear)
          .append(", Quadrimestre: ").append(quadrimestre).append("\n");
        if (grades.isEmpty()) {
            sb.append("  No grades recorded yet.\n");
        } else {
            grades.forEach((subject, grade) ->
                sb.append("  ").append(subject).append(": ").append(grade).append("\n")
            );
        }
        return sb.toString();
    }
}