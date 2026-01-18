package com.smos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student's report card for a specific academic year and quadrimestre.
 * It contains the student, the academic year, the quadrimestre number, and a list of grades.
 */
public class ReportCard {
    private final Student student;
    private final AcademicYear academicYear;
    private final int quadrimestre; // Typically 1 or 2 for a school year
    private final List<Grade> grades;

    /**
     * Constructs a new ReportCard for a given student, academic year, and quadrimestre.
     * Initializes with an empty list of grades.
     *
     * @param student The student to whom this report card belongs.
     * @param academicYear The academic year for this report card.
     * @param quadrimestre The quadrimestre number (e.g., 1 or 2).
     * @throws IllegalArgumentException if student or academicYear is null, or if quadrimestre is not 1 or 2.
     */
    public ReportCard(Student student, AcademicYear academicYear, int quadrimestre) {
        this(student, academicYear, quadrimestre, new ArrayList<>());
    }

    /**
     * Constructs a new ReportCard for a given student, academic year, quadrimestre, and an initial list of grades.
     *
     * @param student The student to whom this report card belongs.
     * @param academicYear The academic year for this report card.
     * @param quadrimestre The quadrimestre number (e.g., 1 or 2).
     * @param grades An initial list of grades for this report card.
     * @throws IllegalArgumentException if student or academicYear is null, if quadrimestre is not 1 or 2,
     *                                  or if the initial grades list is null.
     */
    public ReportCard(Student student, AcademicYear academicYear, int quadrimestre, List<Grade> grades) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null for a report card.");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("Academic year cannot be null for a report card.");
        }
        if (quadrimestre != 1 && quadrimestre != 2) {
            throw new IllegalArgumentException("Quadrimestre must be 1 or 2.");
        }
        if (grades == null) {
            throw new IllegalArgumentException("Grades list cannot be null.");
        }

        this.student = student;
        this.academicYear = academicYear;
        this.quadrimestre = quadrimestre;
        this.grades = new ArrayList<>(grades); // Defensive copy
    }

    /**
     * Returns the student associated with this report card.
     *
     * @return The Student object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Returns the academic year for this report card.
     *
     * @return The AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns the quadrimestre number for this report card.
     *
     * @return The quadrimestre number (1 or 2).
     */
    public int getQuadrimestre() {
        return quadrimestre;
    }

    /**
     * Returns an unmodifiable list of grades in this report card.
     *
     * @return An unmodifiable list of Grade objects.
     */
    public List<Grade> getGrades() {
        return Collections.unmodifiableList(grades);
    }

    /**
     * Adds a grade to this report card.
     *
     * @param grade The Grade object to add.
     * @throws IllegalArgumentException if the grade is null.
     */
    public void addGrade(Grade grade) {
        if (grade == null) {
            throw new IllegalArgumentException("Cannot add a null grade to the report card.");
        }
        this.grades.add(grade);
    }

    /**
     * Retrieves the grade for a specific subject from this report card.
     *
     * @param subject The name of the subject.
     * @return The Grade object for the specified subject, or null if not found.
     */
    public Grade getGradeForSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            return null; // Or throw IllegalArgumentException
        }
        for (Grade grade : grades) {
            if (grade.getSubject().equalsIgnoreCase(subject)) {
                return grade;
            }
        }
        return null;
    }

    /**
     * Compares this ReportCard object to the specified object.
     * The result is true if and only if the argument is not null and is a ReportCard object
     * that represents the same report card (based on student, academic year, and quadrimestre) as this object.
     *
     * @param o The object to compare this ReportCard against.
     * @return true if the given object represents a ReportCard equivalent to this ReportCard, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return quadrimestre == that.quadrimestre &&
               Objects.equals(student, that.student) &&
               Objects.equals(academicYear, that.academicYear);
    }

    /**
     * Returns a hash code for this ReportCard object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(student, academicYear, quadrimestre);
    }

    /**
     * Returns a string representation of this ReportCard object.
     *
     * @return A string representation including student, academic year, quadrimestre, and number of grades.
     */
    @Override
    public String toString() {
        return "ReportCard{" +
               "student=" + student.getName() +
               ", academicYear=" + academicYear.getYear() +
               ", quadrimestre=" + quadrimestre +
               ", gradeCount=" + grades.size() +
               '}';
    }
}