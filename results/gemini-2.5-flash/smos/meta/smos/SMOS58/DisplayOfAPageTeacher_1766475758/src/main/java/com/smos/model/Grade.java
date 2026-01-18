package com.smos.model;

import java.util.Objects;

/**
 * Represents a student's grade in a specific subject.
 * A grade consists of a subject name and a numerical value.
 */
public class Grade {
    private final String subject;
    private final double value;

    /**
     * Constructs a new Grade with the specified subject and value.
     *
     * @param subject The name of the subject (e.g., "Mathematics", "History").
     * @param value The numerical grade value.
     * @throws IllegalArgumentException if the subject is null or empty, or if the grade value is negative.
     */
    public Grade(String subject, double value) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        if (value < 0) { // Assuming grades cannot be negative
            throw new IllegalArgumentException("Grade value cannot be negative.");
        }
        this.subject = subject;
        this.value = value;
    }

    /**
     * Returns the name of the subject for this grade.
     *
     * @return The subject name.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Returns the numerical value of this grade.
     *
     * @return The grade value.
     */
    public double getValue() {
        return value;
    }

    /**
     * Compares this Grade object to the specified object.
     * The result is true if and only if the argument is not null and is a Grade object
     * that represents the same subject and value as this object.
     *
     * @param o The object to compare this Grade against.
     * @return true if the given object represents a Grade equivalent to this Grade, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Double.compare(grade.value, value) == 0 &&
               Objects.equals(subject, grade.subject);
    }

    /**
     * Returns a hash code for this Grade object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(subject, value);
    }

    /**
     * Returns a string representation of this Grade object.
     *
     * @return A string representation including the subject and grade value.
     */
    @Override
    public String toString() {
        return "Grade{" +
               "subject='" + subject + '\'' +
               ", value=" + value +
               '}';
    }
}