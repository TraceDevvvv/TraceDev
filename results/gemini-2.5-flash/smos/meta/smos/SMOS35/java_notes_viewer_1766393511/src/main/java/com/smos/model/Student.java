package com.smos.model;

import java.util.Objects;

/**
 * Represents a student in the School Management and Operations System (SMOS).
 * This class holds basic information about a student, such as their ID and name.
 */
public class Student {
    private String studentId;
    private String name;

    /**
     * Constructs a new Student object with the specified ID and name.
     *
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
     */
    public Student(String studentId, String name) {
        // Validate input to ensure studentId and name are not null or empty
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.studentId = studentId;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Sets the unique identifier of the student.
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
     * Returns the full name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the student.
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
     * Compares this Student object to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same studentId as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
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