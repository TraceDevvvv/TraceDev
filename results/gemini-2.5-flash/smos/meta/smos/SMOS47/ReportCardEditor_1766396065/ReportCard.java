package com.example.reportcardeditor;

import java.util.Objects;

/**
 * Represents a student's report card, encapsulating the student's details and their academic record.
 * This class acts as a container for a {@link Student} object, providing a clear abstraction
 * for report card operations.
 * It is designed to be immutable, meaning once a ReportCard is created, the underlying Student object
 * cannot be changed directly through this class. Any modifications to the student's record
 * would typically result in a new ReportCard instance being created by the {@link ReportCardManager}.
 */
public class ReportCard {
    private final Student student;

    /**
     * Constructs a new ReportCard instance for a given student.
     *
     * @param student The {@link Student} object whose report card this represents. Cannot be null.
     * @throws IllegalArgumentException if the student object is null.
     */
    public ReportCard(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null for a report card.");
        }
        this.student = student;
    }

    /**
     * Returns the student associated with this report card.
     *
     * @return The {@link Student} object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Provides a string representation of the ReportCard, primarily by delegating to the
     * underlying Student object's toString method.
     *
     * @return A formatted string representing the report card.
     */
    @Override
    public String toString() {
        return "--- Report Card ---\n" + student.toString() + "-------------------";
    }

    /**
     * Compares this ReportCard to the specified object. The result is true if and only if
     * the argument is not null and is a ReportCard object that contains an equivalent
     * Student object (based on Student's equals method).
     *
     * @param o The object to compare this ReportCard against.
     * @return true if the given object represents a ReportCard equivalent to this ReportCard, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return student.equals(that.student);
    }

    /**
     * Returns a hash code for this ReportCard.
     *
     * @return A hash code value for this object, based on the underlying student's hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(student);
    }
}