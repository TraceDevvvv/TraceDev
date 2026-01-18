package com.reportcard.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a school class within the report card system.
 * Each class has a unique ID, a name, and a collection of students enrolled in it.
 */
public class SchoolClass {
    private String classId;
    private String name;
    private List<Student> students;

    /**
     * Constructs a new SchoolClass object.
     *
     * @param classId The unique identifier for the class (e.g., "10A", "Grade 5").
     * @param name The name of the class (e.g., "Tenth Grade - Section A", "Primary 5").
     */
    public SchoolClass(String classId, String name) {
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.classId = classId;
        this.name = name;
        this.students = new ArrayList<>(); // Initialize with an empty list
    }

    /**
     * Returns the class ID.
     *
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Sets the class ID.
     *
     * @param classId The new class ID.
     */
    public void setClassId(String classId) {
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty.");
        }
        this.classId = classId;
    }

    /**
     * Returns the name of the class.
     *
     * @return The class name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the class.
     *
     * @param name The new class name.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.name = name;
    }

    /**
     * Returns an unmodifiable list of students enrolled in this class.
     *
     * @return An unmodifiable list of Student objects.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students); // Return an unmodifiable view to prevent external modification
    }

    /**
     * Adds a student to this class.
     *
     * @param student The Student object to add.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        // Prevent adding the same student multiple times based on studentId
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    /**
     * Finds a student in this class by their student ID.
     *
     * @param studentId The ID of the student to find.
     * @return The Student object if found, otherwise null.
     */
    public Student getStudentById(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Overrides the equals method to compare SchoolClass objects based on their classId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return Objects.equals(classId, that.classId);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this SchoolClass object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(classId);
    }

    /**
     * Returns a string representation of the SchoolClass object.
     *
     * @return A string containing the class ID and name.
     */
    @Override
    public String toString() {
        return "SchoolClass{" +
               "classId='" + classId + '\'' +
               ", name='" + name + '\'' +
               ", studentCount=" + students.size() +
               '}';
    }
}