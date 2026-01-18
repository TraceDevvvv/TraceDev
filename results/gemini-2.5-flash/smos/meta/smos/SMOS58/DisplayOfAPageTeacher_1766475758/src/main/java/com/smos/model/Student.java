package com.smos.model;

import java.util.Objects;

/**
 * Represents a student in the school system.
 * A student is identified by a unique ID and has a name.
 */
public class Student {
    private final String id;
    private final String name;

    /**
     * Constructs a new Student with the specified ID and name.
     *
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     * @throws IllegalArgumentException if the ID or name is null or empty.
     */
    public Student(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique ID of the student.
     *
     * @return The student's ID.
     */
    public String getId() {
        return id;
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
     * Compares this Student object to the specified object.
     * The result is true if and only if the argument is not null and is a Student object
     * that represents the same student (based on ID) as this object.
     *
     * @param o The object to compare this Student against.
     * @return true if the given object represents a Student equivalent to this Student, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    /**
     * Returns a hash code for this Student object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of this Student object.
     *
     * @return A string representation including the student's ID and name.
     */
    @Override
    public String toString() {
        return "Student{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}