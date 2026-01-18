package com.school.monitoring.model;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) to hold aggregated monitoring data for a student.
 * This class is used to present the results of the student monitoring query,
 * combining student identification with their total absences and notes for a specific school year.
 */
public class StudentMonitoringReportEntry {
    private String studentId;
    private String studentName;
    private int totalAbsences;
    private int totalNotes;
    private String schoolYear; // Using String for simplicity, matching SchoolYear's identifier

    /**
     * Constructs a new StudentMonitoringReportEntry.
     *
     * @param studentId       The unique ID of the student.
     * @param studentName     The full name of the student.
     * @param totalAbsences   The total number of absences for the student in the specified school year.
     * @param totalNotes      The total number of notes for the student in the specified school year.
     * @param schoolYear      The identifier of the school year (e.g., "2023-2024").
     * @throws IllegalArgumentException if studentId, studentName, or schoolYear is null or empty,
     *                                  or if totalAbsences or totalNotes are negative.
     */
    public StudentMonitoringReportEntry(String studentId, String studentName, int totalAbsences, int totalNotes, String schoolYear) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty for a report entry.");
        }
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty for a report entry.");
        }
        if (totalAbsences < 0) {
            throw new IllegalArgumentException("Total absences cannot be negative.");
        }
        if (totalNotes < 0) {
            throw new IllegalArgumentException("Total notes cannot be negative.");
        }
        if (schoolYear == null || schoolYear.trim().isEmpty()) {
            throw new IllegalArgumentException("School year cannot be null or empty for a report entry.");
        }

        this.studentId = studentId;
        this.studentName = studentName;
        this.totalAbsences = totalAbsences;
        this.totalNotes = totalNotes;
        this.schoolYear = schoolYear;
    }

    /**
     * Returns the unique ID of the student.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the full name of the student.
     * @return The student's full name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Returns the total number of absences for the student in the specified school year.
     * @return The total absences.
     */
    public int getTotalAbsences() {
        return totalAbsences;
    }

    /**
     * Returns the total number of notes for the student in the specified school year.
     * @return The total notes.
     */
    public int getTotalNotes() {
        return totalNotes;
    }

    /**
     * Returns the identifier of the school year.
     * @return The school year identifier.
     */
    public String getSchoolYear() {
        return schoolYear;
    }

    @Override
    public String toString() {
        return "StudentMonitoringReportEntry{" +
               "studentId='" + studentId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", totalAbsences=" + totalAbsences +
               ", totalNotes=" + totalNotes +
               ", schoolYear='" + schoolYear + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentMonitoringReportEntry that = (StudentMonitoringReportEntry) o;
        return totalAbsences == that.totalAbsences &&
               totalNotes == that.totalNotes &&
               Objects.equals(studentId, that.studentId) &&
               Objects.equals(studentName, that.studentName) &&
               Objects.equals(schoolYear, that.schoolYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studentName, totalAbsences, totalNotes, schoolYear);
    }
}