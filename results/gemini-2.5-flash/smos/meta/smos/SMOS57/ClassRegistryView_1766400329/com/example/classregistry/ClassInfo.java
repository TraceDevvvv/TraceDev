package com.example.classregistry;

import java.util.Objects;

/**
 * Represents a class with its details, such as class ID and class name.
 * This object is used to store information about a course taught by a professor.
 */
public class ClassInfo {
    private String classId;
    private String className;

    /**
     * Constructs a new ClassInfo instance.
     * @param classId The unique identifier for the class.
     * @param className The name of the class.
     */
    public ClassInfo(String classId, String className) {
        this.classId = classId;
        this.className = className;
    }

    /**
     * Returns the unique ID of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Returns the name of the class.
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Overrides the equals method to compare ClassInfo objects based on their classId.
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return Objects.equals(classId, classInfo.classId);
    }

    /**
     * Overrides the hashCode method to generate a hash code based on the classId.
     * @return The hash code for this ClassInfo object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }

    /**
     * Returns a string representation of the ClassInfo object.
     * @return A string containing the class ID and class name.
     */
    @Override
    public String toString() {
        return "ClassInfo{" +
               "classId='" + classId + '\'' +
               ", className='" + className + '\'' +
               '}';
    }
}