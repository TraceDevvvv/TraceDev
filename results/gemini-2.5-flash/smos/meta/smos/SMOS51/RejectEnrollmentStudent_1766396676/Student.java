package com.example.smos.model;

/**
 * Represents a student in the system.
 * This class holds basic information about a student, such as their ID and name.
 */
public class Student {
    private String studentId;
    private String name;
    private String email;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student.
     * @param name The full name of the student.
     * @param email The email address of the student.
     */
    public Student(String studentId, String name, String email) {
        // Basic validation for studentId
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        // Basic validation for name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        // Basic validation for email
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Student email cannot be null or empty.");
        }

        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the unique identifier of the student.
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the full name of the student.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address of the student.
     * @return The student's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Provides a string representation of the Student object.
     * @return A string containing the student's ID, name, and email.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }

    /**
     * Compares this Student object to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that represents the same student ID.
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this student, false otherwise.
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
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}