package com.smos.model;

import java.util.Objects;

/**
 * Represents a teacher in the school system.
 * A teacher is identified by a unique ID and has a name.
 */
public class Teacher {
    private final String id;
    private final String name;

    /**
     * Constructs a new Teacher with the specified ID and name.
     *
     * @param id The unique identifier for the teacher.
     * @param name The full name of the teacher.
     * @throws IllegalArgumentException if the ID or name is null or empty.
     */
    public Teacher(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty.");
        }
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the unique ID of the teacher.
     *
     * @return The teacher's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the full name of the teacher.
     *
     * @return The teacher's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Compares this Teacher object to the specified object.
     * The result is true if and only if the argument is not null and is a Teacher object
     * that represents the same teacher (based on ID) as this object.
     *
     * @param o The object to compare this Teacher against.
     * @return true if the given object represents a Teacher equivalent to this Teacher, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    /**
     * Returns a hash code for this Teacher object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of this Teacher object.
     *
     * @return A string representation including the teacher's ID and name.
     */
    @Override
    public String toString() {
        return "Teacher{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}