package com.example.teachermanagementsystem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a teacher in the system.
 * Each teacher has a unique ID, a name, and a collection of teachings assigned to them.
 */
public class Teacher {
    private String id;
    private String name;
    // Teachings assigned to this teacher, stored as a set of Teaching objects for uniqueness
    private Set<Teaching> assignedTeachings;

    /**
     * Constructs a new Teacher instance.
     * @param id A unique identifier for the teacher.
     * @param name The full name of the teacher.
     */
    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
        this.assignedTeachings = new HashSet<>();
    }

    /**
     * Returns the unique ID of the teacher.
     * @return The teacher's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the teacher.
     * @return The teacher's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns an unmodifiable set of teachings currently assigned to this teacher.
     * This prevents external modification of the teacher's assigned teachings directly.
     * @return An unmodifiable set of assigned Teaching objects.
     */
    public Set<Teaching> getAssignedTeachings() {
        return Collections.unmodifiableSet(assignedTeachings);
    }

    /**
     * Assigns a teaching to this teacher.
     * If the teaching is already assigned, the set remains unchanged.
     * @param teaching The teaching to assign.
     * @return true if the teaching was successfully assigned (i.e., it was not already assigned), false otherwise.
     */
    public boolean assignTeaching(Teaching teaching) {
        Objects.requireNonNull(teaching, "Teaching cannot be null.");
        return assignedTeachings.add(teaching);
    }

    /**
     * Removes a teaching from this teacher.
     * If the teaching is not assigned, the set remains unchanged.
     * @param teaching The teaching to remove.
     * @return true if the teaching was successfully removed (i.e., it was previously assigned), false otherwise.
     */
    public boolean removeTeaching(Teaching teaching) {
        Objects.requireNonNull(teaching, "Teaching cannot be null.");
        return assignedTeachings.remove(teaching);
    }

    /**
     * Provides a string representation of the Teacher, including their ID, name, and assigned teachings.
     * @return A formatted string representing the teacher.
     */
    @Override
    public String toString() {
        return "Teacher{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", assignedTeachings=[" + assignedTeachings.stream().map(Teaching::getName).collect(Collectors.joining(", ")) + "]" +
               '}';
    }

    /**
     * Compares this Teacher to the specified object. The result is true if and only if
     * the argument is not null and is a Teacher object that has the same ID as this object.
     * @param o The object to compare this Teacher against.
     * @return true if the given object represents a Teacher equivalent to this teacher, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}