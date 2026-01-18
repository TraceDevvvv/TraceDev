package com.example.teachermanagementsystem;

import java.util.Objects;

/**
 * Represents a specific teaching (subject/course) that can be assigned to a teacher.
 * Each teaching has a unique ID, a name (e.g., "Mathematics"), and is associated with a specific class.
 */
public class Teaching {
    private String id;
    private String name;
    private String classId; // The ID of the class this teaching belongs to

    /**
     * Constructs a new Teaching instance.
     * @param id A unique identifier for the teaching.
     * @param name The name of the teaching (e.g., "Mathematics").
     * @param classId The ID of the class this teaching is offered in.
     */
    public Teaching(String id, String name, String classId) {
        this.id = id;
        this.name = name;
        this.classId = classId;
    }

    /**
     * Returns the unique ID of the teaching.
     * @return The teaching's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the teaching.
     * @return The teaching's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the class this teaching belongs to.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Provides a string representation of the Teaching, primarily its name.
     * @return The teaching name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Compares this Teaching to the specified object. The result is true if and only if
     * the argument is not null and is a Teaching object that has the same ID as this object.
     * @param o The object to compare this Teaching against.
     * @return true if the given object represents a Teaching equivalent to this teaching, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return Objects.equals(id, teaching.id);
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