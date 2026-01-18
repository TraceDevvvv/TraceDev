package com.example.domain;

import java.util.Objects;

/**
 * Represents a student in the system.
 * Contains basic student information and provides
 * methods to access this information.
 */
public class Student {
    private final String studentId;
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a new Student.
     * @param studentId the unique identifier for the student
     * @param firstName the student's first name
     * @param lastName the student's last name
     */
    public Student(String studentId, String firstName, String lastName) {
        this.studentId = Objects.requireNonNull(studentId, "studentId must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the full name of the student.
     * @return a string containing first and last name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", fullName='" + getFullName() + '\'' +
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