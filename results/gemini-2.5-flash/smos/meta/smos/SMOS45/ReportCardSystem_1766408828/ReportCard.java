package com.reportcard.system;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a student's report card for a specific academic year.
 * It stores grades for various months.
 */
public class ReportCard {
    private final String studentId;
    private final AcademicYear academicYear;
    private final Map<String, Integer> grades; // Key: Month name (e.g., "January"), Value: Grade (0-100)

    /**
     * Constructs a new ReportCard for a specific student and academic year.
     *
     * @param studentId The unique identifier of the student this report card belongs to. Must not be null or empty.
     * @param academicYear The academic year this report card is for. Must not be null.
     * @throws IllegalArgumentException if studentId is null or empty, or academicYear is null.
     */
    public ReportCard(String studentId, AcademicYear academicYear) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty for a report card.");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("AcademicYear cannot be null for a report card.");
        }
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.grades = new HashMap<>();
    }

    /**
     * Returns the student ID associated with this report card.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the academic year associated with this report card.
     *
     * @return The AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Adds or updates a grade for a specific month.
     * Grades should be between 0 and 100.
     *
     * @param month The name of the month (e.g., "January", "February"). Must not be null or empty.
     * @param grade The grade for the month. Must be between 0 and 100.
     * @throws IllegalArgumentException if month is null or empty, or grade is out of range.
     */
    public void addGrade(String month, int grade) {
        if (month == null || month.trim().isEmpty()) {
            throw new IllegalArgumentException("Month name cannot be null or empty.");
        }
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100. Received: " + grade);
        }
        this.grades.put(month, grade);
    }

    /**
     * Retrieves the grade for a specific month.
     *
     * @param month The name of the month. Must not be null or empty.
     * @return The grade for the specified month, or null if no grade is recorded for that month.
     * @throws IllegalArgumentException if month is null or empty.
     */
    public Integer getGrade(String month) {
        if (month == null || month.trim().isEmpty()) {
            throw new IllegalArgumentException("Month name cannot be null or empty when retrieving grade.");
        }
        return grades.get(month);
    }

    /**
     * Retrieves a map of grades for a list of specified months.
     * Only months for which grades exist will be included in the returned map.
     *
     * @param months A list of month names for which to retrieve grades. Must not be null.
     * @return An unmodifiable map where keys are month names and values are their corresponding grades.
     * @throws IllegalArgumentException if the months list is null.
     */
    public Map<String, Integer> getGradesForMonths(List<String> months) {
        if (months == null) {
            throw new IllegalArgumentException("List of months cannot be null.");
        }
        Map<String, Integer> selectedGrades = new HashMap<>();
        for (String month : months) {
            if (month != null && grades.containsKey(month)) {
                selectedGrades.put(month, grades.get(month));
            }
        }
        return Collections.unmodifiableMap(selectedGrades);
    }

    /**
     * Returns an unmodifiable map of all grades recorded in this report card.
     *
     * @return An unmodifiable map of all grades.
     */
    public Map<String, Integer> getAllGrades() {
        return Collections.unmodifiableMap(grades);
    }

    /**
     * Overrides the toString method to provide a meaningful string representation of the ReportCard object.
     *
     * @return A string in the format "ReportCard[studentId='ID', academicYear='YYYY-YYYY', grades={...}]".
     */
    @Override
    public String toString() {
        return "ReportCard[studentId='" + studentId +
                "', academicYear='" + academicYear.getYearIdentifier() +
                "', grades=" + grades.entrySet().stream()
                                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                                    .collect(Collectors.joining(", ", "{", "}")) +
                "]";
    }

    /**
     * Compares this ReportCard object with another object for equality.
     * Two ReportCard objects are considered equal if they belong to the same student
     * and the same academic year.
     *
     * @param o The object to compare with.
     * @return true if the given object is an instance of ReportCard and has the same student ID and academic year, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return studentId.equals(that.studentId) &&
                academicYear.equals(that.academicYear);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, academicYear);
    }
}