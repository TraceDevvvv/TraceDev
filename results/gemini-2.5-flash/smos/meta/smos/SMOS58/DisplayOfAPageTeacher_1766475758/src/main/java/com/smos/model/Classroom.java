package com.smos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a classroom in the school system.
 * A classroom has a unique name and contains a list of students.
 */
public class Classroom {
    private final String name;
    private final List<Student> students; // List of students in this classroom

    /**
     * Constructs a new Classroom with the specified name and an empty list of students.
     *
     * @param name The name of the classroom (e.g., "1A", "2B").
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public Classroom(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Classroom name cannot be null or empty.");
        }
        this.name = name;
        this.students = new ArrayList<>();
    }

    /**
     * Constructs a new Classroom with the specified name and a given list of students.
     *
     * @param name The name of the classroom.
     * @param students A list of Student objects belonging to this classroom.
     * @throws IllegalArgumentException if the name is null or empty, or if the students list is null.
     */
    public Classroom(String name, List<Student> students) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Classroom name cannot be null or empty.");
        }
        if (students == null) {
            throw new IllegalArgumentException("Students list cannot be null.");
        }
        this.name = name;
        this.students = new ArrayList<>(students); // Create a defensive copy
    }

    /**
     * Returns the name of the classroom.
     *
     * @return The classroom name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable list of students in this classroom.
     *
     * @return An unmodifiable list of Student objects.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Adds a student to this classroom.
     *
     * @param student The student to add.
     * @throws IllegalArgumentException if the student is null.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add a null student to the classroom.");
        }
        this.students.add(student);
    }

    /**
     * Compares this Classroom object to the specified object.
     * The result is true if and only if the argument is not null and is a Classroom object
     * that represents the same classroom (based on name) as this object.
     *
     * @param o The object to compare this Classroom against.
     * @return true if the given object represents a Classroom equivalent to this Classroom, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(name, classroom.name);
    }

    /**
     * Returns a hash code for this Classroom object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Returns a string representation of this Classroom object.
     *
     * @return A string representation including the classroom name and the number of students.
     */
    @Override
    public String toString() {
        return "Classroom{" +
               "name='" + name + '\'' +
               ", studentCount=" + students.size() +
               '}';
    }
}