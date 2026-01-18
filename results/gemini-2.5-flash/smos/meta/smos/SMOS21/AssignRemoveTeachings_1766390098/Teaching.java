// Teaching.java
package com.example.teachingsystem;

/**
 * Represents a Teaching entity in the system.
 * Each teaching has a unique identifier and a name.
 */
public class Teaching {
    private String teachingId;
    private String name;

    /**
     * Constructs a new Teaching instance.
     *
     * @param teachingId A unique identifier for the teaching.
     * @param name The name of the teaching (e.g., "Mathematics", "Physics").
     */
    public Teaching(String teachingId, String name) {
        if (teachingId == null || teachingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty.");
        }
        this.teachingId = teachingId;
        this.name = name;
    }

    /**
     * Returns the unique identifier of the teaching.
     *
     * @return The teaching's ID.
     */
    public String getTeachingId() {
        return teachingId;
    }

    /**
     * Returns the name of the teaching.
     *
     * @return The teaching's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the teaching.
     *
     * @param name The new name for the teaching.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Provides a string representation of the Teaching object.
     *
     * @return A string containing the teaching's ID and name.
     */
    @Override
    public String toString() {
        return "Teaching{" +
               "teachingId='" + teachingId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }

    /**
     * Checks if this Teaching object is equal to another object.
     * Equality is based on the teachingId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teaching teaching = (Teaching) o;
        return teachingId.equals(teaching.teachingId);
    }

    /**
     * Returns a hash code value for the Teaching object.
     * The hash code is based on the teachingId.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return teachingId.hashCode();
    }
}