package com.reportcard.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents an individual student in the Report Card System.
 * Each student has a unique ID, a name, and can have multiple report cards
 * associated with different academic years.
 */
public class Student {
    private final String studentId;
    private final String name;
    // A map to store report cards, keyed by AcademicYear object.
    // This allows a student to have a report card for each academic year.
    private final Map<AcademicYear, ReportCard> reportCards;

    /**
     * Constructs a new Student with the specified ID and name.
     * Initializes an empty map for report cards.
     *
     * @param studentId A unique string identifier for the student. Must not be null or empty.
     * @param name The full name of the student. Must not be null or empty.
     * @throws IllegalArgumentException if studentId or name is null or empty.
     */
    public Student(String studentId, String name) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.studentId = studentId;
        this.name = name;
        this.reportCards = new HashMap<>();
    }

    /**
     * Returns the unique identifier of this student.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the name of this student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a report card for a specific academic year to this student's record.
     * If a report card for the given academic year already exists, it will be overwritten.
     *
     * @param academicYear The AcademicYear object to associate the report card with. Must not be null.
     * @param reportCard The ReportCard object to add. Must not be null.
     * @throws IllegalArgumentException if academicYear or reportCard is null.
     */
    public void addReportCard(AcademicYear academicYear, ReportCard reportCard) {
        if (academicYear == null) {
            throw new IllegalArgumentException("AcademicYear cannot be null when adding a report card.");
        }
        if (reportCard == null) {
            throw new IllegalArgumentException("ReportCard cannot be null when adding to a student.");
        }
        this.reportCards.put(academicYear, reportCard);
    }

    /**
     * Retrieves the report card for a specific academic year.
     *
     * @param academicYear The AcademicYear object for which to retrieve the report card. Must not be null.
     * @return The ReportCard object for the specified academic year, or null if not found.
     * @throws IllegalArgumentException if academicYear is null.
     */
    public ReportCard getReportCard(AcademicYear academicYear) {
        if (academicYear == null) {
            throw new IllegalArgumentException("AcademicYear cannot be null when retrieving a report card.");
        }
        return reportCards.get(academicYear);
    }

    /**
     * Overrides the toString method to provide a meaningful string representation of the Student object.
     *
     * @return A string in the format "Student[studentId='ID', name='Name']".
     */
    @Override
    public String toString() {
        return "Student[studentId='" + studentId + "', name='" + name + "']";
    }

    /**
     * Compares this Student object with another object for equality.
     * Two Student objects are considered equal if their student IDs are the same.
     *
     * @param o The object to compare with.
     * @return true if the given object is an instance of Student and has the same student ID, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}