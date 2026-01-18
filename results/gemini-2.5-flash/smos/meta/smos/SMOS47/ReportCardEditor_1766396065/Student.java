package com.example.reportcardeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student with their unique identifier, name, and a list of subjects they are taking.
 * This class is designed to be immutable to ensure data integrity once a student object is created.
 */
public class Student {
    private final String studentId;
    private final String name;
    private final List<Subject> subjects; // List of subjects the student is enrolled in

    /**
     * Constructs a new Student instance.
     *
     * @param studentId A unique identifier for the student. Cannot be null or empty.
     * @param name The full name of the student. Cannot be null or empty.
     * @param subjects A list of Subject objects representing the student's enrolled subjects and grades.
     *                 Can be empty but not null. The list is defensively copied.
     * @throws IllegalArgumentException if studentId or name is null/empty, or if subjects list is null.
     */
    public Student(String studentId, String name, List<Subject> subjects) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (subjects == null) {
            throw new IllegalArgumentException("Subjects list cannot be null. Use an empty list if no subjects.");
        }

        this.studentId = studentId;
        this.name = name;
        // Defensively copy the list to ensure immutability
        this.subjects = Collections.unmodifiableList(new ArrayList<>(subjects));
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
     * Returns the full name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable list of subjects for this student.
     *
     * @return An unmodifiable list of Subject objects.
     */
    public List<Subject> getSubjects() {
        return subjects; // Returns the unmodifiable list
    }

    /**
     * Provides a string representation of the Student object, including their ID, name, and subjects.
     *
     * @return A formatted string representing the student.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Subjects:\n");
        if (subjects.isEmpty()) {
            sb.append("  No subjects enrolled.\n");
        } else {
            for (Subject subject : subjects) {
                sb.append("  - ").append(subject.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Compares this Student to the specified object. The result is true if and only if
     * the argument is not null and is a Student object that has the same student ID
     * as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this Student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId.equals(student.studentId);
    }

    /**
     * Returns a hash code for this Student.
     *
     * @return A hash code value for this object, based on the student ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}