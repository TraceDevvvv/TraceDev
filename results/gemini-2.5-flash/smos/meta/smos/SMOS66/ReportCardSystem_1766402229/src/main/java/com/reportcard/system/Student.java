package com.reportcard.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student in the report card system.
 * Each student has a unique ID, a name, and a collection of report cards.
 */
public class Student {
    private String studentId;
    private String name;
    private List<ReportCard> reportCards;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
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
        this.reportCards = new ArrayList<>(); // Initialize with an empty list
    }

    /**
     * Returns the student's ID.
     *
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the student's ID.
     *
     * @param studentId The new student ID.
     */
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentId = studentId;
    }

    /**
     * Returns the student's name.
     *
     * @return The student name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name.
     *
     * @param name The new student name.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Returns the list of report cards associated with this student.
     *
     * @return A list of ReportCard objects.
     */
    public List<ReportCard> getReportCards() {
        return new ArrayList<>(reportCards); // Return a defensive copy
    }

    /**
     * Adds a report card to the student's list of report cards.
     *
     * @param reportCard The ReportCard object to add.
     */
    public void addReportCard(ReportCard reportCard) {
        if (reportCard == null) {
            throw new IllegalArgumentException("Report card cannot be null.");
        }
        this.reportCards.add(reportCard);
    }

    /**
     * Finds a specific report card for the student based on academic year and quadrimestre.
     *
     * @param academicYear The academic year of the report card.
     * @param quadrimestre The quadrimestre (semester) of the report card.
     * @return The matching ReportCard object, or null if not found.
     */
    public ReportCard getReportCard(String academicYear, String quadrimestre) {
        for (ReportCard rc : reportCards) {
            if (rc.getAcademicYear().equals(academicYear) && rc.getQuadrimestre().equals(quadrimestre)) {
                return rc;
            }
        }
        return null;
    }

    /**
     * Overrides the equals method to compare Student objects based on their studentId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this Student object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return A string containing the student's ID and name.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}