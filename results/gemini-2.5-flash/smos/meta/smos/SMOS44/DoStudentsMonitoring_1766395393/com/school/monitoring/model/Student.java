package com.school.monitoring.model;

import java.util.Objects;

/**
 * Represents a student with an ID, first name, and last name.
 */
public class Student {
    private String studentId;
    private String firstName;
    private String lastName;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param firstName The first name of the student.
     * @param lastName  The last name of the student.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Student(String studentId, String firstName, String lastName) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }

        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
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
     * @throws IllegalArgumentException if the student ID is null or empty.
     */
    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        this.studentId = studentId;
    }

    /**
     * Returns the first name of the student.
     *
     * @return The student's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName The new first name.
     * @throws IllegalArgumentException if the first name is null or empty.
     */
    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the student.
     *
     * @return The student's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName The new last name.
     * @throws IllegalArgumentException if the last name is null or empty.
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        this.lastName = lastName;
    }

    /**
     * Returns the full name of the student (first name + last name).
     *
     * @return The full name.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}