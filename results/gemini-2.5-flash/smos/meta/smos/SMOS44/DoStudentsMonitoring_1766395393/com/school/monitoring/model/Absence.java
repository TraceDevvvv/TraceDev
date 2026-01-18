package com.school.monitoring.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an absence record for a student on a specific date
 * within a particular school year.
 */
public class Absence {
    private String studentId;
    private LocalDate absenceDate;
    private String reason; // Optional reason for absence
    private SchoolYear schoolYear;

    /**
     * Constructs a new Absence record.
     *
     * @param studentId    The ID of the student who was absent.
     * @param absenceDate  The date of the absence.
     * @param reason       An optional reason for the absence. Can be null or empty.
     * @param schoolYear   The school year during which the absence occurred.
     * @throws IllegalArgumentException if studentId, absenceDate, or schoolYear is null.
     */
    public Absence(String studentId, LocalDate absenceDate, String reason, SchoolYear schoolYear) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty for an absence record.");
        }
        if (absenceDate == null) {
            throw new IllegalArgumentException("Absence date cannot be null.");
        }
        if (schoolYear == null) {
            throw new IllegalArgumentException("School year cannot be null for an absence record.");
        }

        this.studentId = studentId;
        this.absenceDate = absenceDate;
        this.reason = (reason != null) ? reason.trim() : ""; // Ensure reason is not null, trim whitespace
        this.schoolYear = schoolYear;
    }

    /**
     * Returns the ID of the student associated with this absence.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student associated with this absence.
     *
     * @param studentId The new student ID.
     * @throws IllegalArgumentException if studentId is null or empty.
     */
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentId = studentId;
    }

    /**
     * Returns the date of the absence.
     *
     * @return The absence date.
     */
    public LocalDate getAbsenceDate() {
        return absenceDate;
    }

    /**
     * Sets the date of the absence.
     *
     * @param absenceDate The new absence date.
     * @throws IllegalArgumentException if absenceDate is null.
     */
    public void setAbsenceDate(LocalDate absenceDate) {
        if (absenceDate == null) {
            throw new IllegalArgumentException("Absence date cannot be null.");
        }
        this.absenceDate = absenceDate;
    }

    /**
     * Returns the reason for the absence.
     *
     * @return The reason for absence, or an empty string if no reason was provided.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the reason for the absence.
     *
     * @param reason The new reason for absence. Can be null or empty.
     */
    public void setReason(String reason) {
        this.reason = (reason != null) ? reason.trim() : "";
    }

    /**
     * Returns the school year associated with this absence.
     *
     * @return The school year.
     */
    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    /**
     * Sets the school year associated with this absence.
     *
     * @param schoolYear The new school year.
     * @throws IllegalArgumentException if schoolYear is null.
     */
    public void setSchoolYear(SchoolYear schoolYear) {
        if (schoolYear == null) {
            throw new IllegalArgumentException("School year cannot be null.");
        }
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "Absence{" +
               "studentId='" + studentId + '\'' +
               ", absenceDate=" + absenceDate +
               ", reason='" + reason + '\'' +
               ", schoolYear=" + schoolYear.getYearIdentifier() +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return Objects.equals(studentId, absence.studentId) &&
               Objects.equals(absenceDate, absence.absenceDate) &&
               Objects.equals(schoolYear, absence.schoolYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, absenceDate, schoolYear);
    }
}