// Student.java
package com.school.admin.delay;

import java.util.Objects;

/**
 * Represents a student in the school system.
 * Each student has a unique ID and a name.
 */
public class Student {
    private final String studentId;
    private final String name;

    /**
     * Constructs a new Student object.
     *
     * @param studentId The unique identifier for the student. Must not be null or empty.
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
    }

    /**
     * Returns the unique ID of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this student to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same student ID
     * as this object.
     *
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
     * Returns a hash code value for the student.
     * The hash code is based on the student's ID.
     *
     * @return A hash code value for this student.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * Returns a string representation of the Student object.
     *
     * @return A string in the format "Student{studentId='...', name='...'}".
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}