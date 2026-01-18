package com.example.editabsence;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an absence record for a student on a specific date and of a specific type.
 * Absence objects are immutable once created.
 */
public class Absence {
    private final String studentId; // The ID of the student this absence belongs to
    private final LocalDate date;    // The date of the absence
    private final AbsenceType type;  // The type of absence (e.g., JUSTIFIED, UNJUSTIFIED)
    private final String reason;     // Optional reason for the absence

    /**
     * Enum to define different types of absences.
     */
    public enum AbsenceType {
        JUSTIFIED,
        UNJUSTIFIED,
        SICK_LEAVE,
        OTHER
    }

    /**
     * Constructs a new Absence object.
     *
     * @param studentId The unique identifier of the student.
     * @param date      The date on which the absence occurred.
     * @param type      The type of absence (e.g., JUSTIFIED, UNJUSTIFIED).
     * @param reason    An optional reason or description for the absence. Can be null or empty.
     * @throws IllegalArgumentException if studentId or date or type is null.
     */
    public Absence(String studentId, LocalDate date, AbsenceType type, String reason) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Absence date cannot be null.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Absence type cannot be null.");
        }

        this.studentId = studentId;
        this.date = date;
        this.type = type;
        this.reason = (reason == null || reason.trim().isEmpty()) ? "" : reason.trim();
    }

    /**
     * Gets the unique identifier of the student associated with this absence.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the date of the absence.
     *
     * @return The LocalDate object representing the absence date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the type of the absence.
     *
     * @return The AbsenceType enum value.
     */
    public AbsenceType getType() {
        return type;
    }

    /**
     * Gets the reason for the absence.
     *
     * @return The reason string, or an empty string if no reason was provided.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Overrides the equals method to compare Absence objects.
     * Two absences are considered equal if they belong to the same student,
     * occurred on the same date, and are of the same type.
     * The reason is not considered for equality.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return studentId.equals(absence.studentId) &&
               date.equals(absence.date) &&
               type == absence.type;
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this Absence object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, date, type);
    }

    /**
     * Provides a string representation of the Absence object.
     *
     * @return A string containing the student ID, date, type, and reason of the absence.
     */
    @Override
    public String toString() {
        return "Absence{" +
               "studentId='" + studentId + '\'' +
               ", date=" + date +
               ", type=" + type +
               ", reason='" + reason + '\'' +
               '}';
    }
}