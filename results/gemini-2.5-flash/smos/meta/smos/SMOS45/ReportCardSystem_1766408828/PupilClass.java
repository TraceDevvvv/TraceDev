package com.reportcard.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a pupil class in the Report Card System.
 * Each class has a unique identifier and can contain multiple students.
 */
public class PupilClass {
    private final String classIdentifier;
    private final List<Student> students; // List of students belonging to this class

    /**
     * Constructs a new PupilClass with the specified identifier.
     * Initializes an empty list of students.
     *
     * @param classIdentifier A string representing the unique identifier for the class (e.g., "Grade 10A").
     *                        Must not be null or empty.
     * @throws IllegalArgumentException if classIdentifier is null or empty.
     */
    public PupilClass(String classIdentifier) {
        if (classIdentifier == null || classIdentifier.trim().isEmpty()) {
            throw new IllegalArgumentException("Class identifier cannot be null or empty.");
        }
        this.classIdentifier = classIdentifier;
        this.students = new ArrayList<>();
    }

    /**
     * Returns the unique identifier of this pupil class.
     *
     * @return The class identifier.
     */
    public String getClassIdentifier() {
        return classIdentifier;
    }

    /**
     * Adds a student to this class.
     *
     * @param student The Student object to add.
     * @throws IllegalArgumentException if the student is null or already exists in the class.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add a null student to the class.");
        }
        if (students.contains(student)) {
            throw new IllegalArgumentException("Student " + student.getName() + " (ID: " + student.getStudentId() + ") already exists in class " + classIdentifier + ".");
        }
        this.students.add(student);
    }

    /**
     * Removes a student from this class.
     *
     * @param student The Student object to remove.
     * @return true if the student was successfully removed, false otherwise.
     * @throws IllegalArgumentException if the student is null.
     */
    public boolean removeStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot remove a null student from the class.");
        }
        return this.students.remove(student);
    }

    /**
     * Returns an unmodifiable list of students in this class.
     *
     * @return A List of Student objects.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Overrides the toString method to provide a meaningful string representation of the PupilClass object.
     *
     * @return A string in the format "PupilClass[classIdentifier='ClassName', studentsCount=X]".
     */
    @Override
    public String toString() {
        return "PupilClass[classIdentifier='" + classIdentifier + "', studentsCount=" + students.size() + "]";
    }

    /**
     * Compares this PupilClass object with another object for equality.
     * Two PupilClass objects are considered equal if their class identifiers are the same.
     *
     * @param o The object to compare with.
     * @return true if the given object is an instance of PupilClass and has the same class identifier, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PupilClass that = (PupilClass) o;
        return classIdentifier.equals(that.classIdentifier);
    }

    /**
     * Returns a hash code value for the object.
     * This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(classIdentifier);
    }
}