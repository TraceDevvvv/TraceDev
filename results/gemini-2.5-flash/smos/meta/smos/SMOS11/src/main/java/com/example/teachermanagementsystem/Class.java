package com.example.teachermanagementsystem;

import java.util.Objects;

/**
 * Represents a class (e.g., Grade 10A, Math 101) within an academic year.
 * Each class has a unique ID, a name, and is associated with a specific academic year.
 */
public class Class {
    private String id;
    private String name;
    private String academicYearId; // Foreign key to AcademicYear

    /**
     * Constructs a new Class instance.
     * @param id A unique identifier for the class.
     * @param name The name of the class (e.g., "Grade 10A").
     * @param academicYearId The ID of the academic year this class belongs to.
     */
    public Class(String id, String name, String academicYearId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
    }

    /**
     * Returns the unique ID of the class.
     * @return The class's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the class.
     * @return The class's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the academic year this class belongs to.
     * @return The academic year ID.
     */
    public String getAcademicYearId() {
        return academicYearId;
    }

    /**
     * Provides a string representation of the Class, primarily its name.
     * @return The class name.
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Compares this Class to the specified object. The result is true if and only if
     * the argument is not null and is a Class object that has the same ID as this object.
     * @param o The object to compare this Class against.
     * @return true if the given object represents a Class equivalent to this class, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(id, aClass.id);
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