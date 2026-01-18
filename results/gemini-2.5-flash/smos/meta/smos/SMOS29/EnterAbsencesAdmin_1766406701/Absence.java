package com.example.absencesystem;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents the type of absence record.
 * Can be either an ABSENCE or a DELAY.
 */
enum AbsenceType {
    ABSENCE,
    DELAY
}

/**
 * Represents an absence or delay record for a specific student on a given date.
 * Each record includes the student, the date, the type of absence (absence or delay),
 * and an optional reason.
 */
public class Absence {
    private final Student student;
    private final LocalDate date;
    private final AbsenceType type;
    private final String reason; // Optional reason for absence/delay

    /**
     * Constructs a new Absence record.
     *
     * @param student The student associated with this absence/delay. Cannot be null.
     * @param date    The date of the absence/delay. Cannot be null.
     * @param type    The type of absence (ABSENCE or DELAY). Cannot be null.
     * @param reason  An optional reason for the absence/delay. Can be null or empty.
     * @throws IllegalArgumentException if student, date, or type is null.
     */
    public Absence(Student student, LocalDate date, AbsenceType type, String reason) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null for an absence record.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null for an absence record.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Absence type cannot be null for an absence record.");
        }

        this.student = student;
        this.date = date;
        this.type = type;
        this.reason = (reason != null && !reason.trim().isEmpty()) ? reason.trim() : null;
    }

    /**
     * Returns the student associated with this absence/delay record.
     *
     * @return The Student object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the date of the absence/delay.
     *
     * @return The LocalDate of the record.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Returns the type of absence (ABSENCE or DELAY).
     *
     * @return The AbsenceType enum value.
     */
    public AbsenceType getType() {
        return type;
    }

    /**
     * Returns the reason for the absence/delay.
     *
     * @return The reason string, or null if no reason was provided.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Compares this Absence object to the specified object. The result is true if and only if
     * the argument is not null and is an Absence object that has the same student, date, and type
     * as this object. The reason is not considered for equality.
     *
     * @param o The object to compare this Absence against.
     * @return true if the given object represents an Absence equivalent to this one, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return student.equals(absence.student) &&
               date.equals(absence.date) &&
               type == absence.type;
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap. The hash code is based on the student, date, and type.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(student, date, type);
    }

    /**
     * Provides a string representation of the Absence object.
     *
     * @return A string containing the student's name, date, type, and reason (if available).
     */
    @Override
    public String toString() {
        return "Absence{" +
               "student=" + student.getName() +
               ", date=" + date +
               ", type=" + type +
               ", reason='" + (reason != null ? reason : "N/A") + '\'' +
               '}';
    }
}