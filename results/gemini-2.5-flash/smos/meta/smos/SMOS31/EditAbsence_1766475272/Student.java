package com.example.editabsence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student in the system, including their personal information
 * and a record of their absences.
 */
public class Student {
    private String studentId;
    private String name;
    private String parentEmail;
    private List<Absence> absences; // List to store absence records for this student

    /**
     * Constructs a new Student object.
     *
     * @param studentId   The unique identifier for the student.
     * @param name        The full name of the student.
     * @param parentEmail The email address of the student's parent for notifications.
     */
    public Student(String studentId, String name, String parentEmail) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("Parent email cannot be null or empty.");
        }

        this.studentId = studentId;
        this.name = name;
        this.parentEmail = parentEmail;
        this.absences = new ArrayList<>(); // Initialize the list of absences
    }

    /**
     * Gets the unique identifier of the student.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Gets the full name of the student.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email address of the student's parent.
     *
     * @return The parent's email address.
     */
    public String getParentEmail() {
        return parentEmail;
    }

    /**
     * Adds an absence record for the student.
     * Ensures that duplicate absences (same date and type) are not added.
     *
     * @param absence The Absence object to add.
     * @return true if the absence was added successfully, false if it already exists.
     */
    public boolean addAbsence(Absence absence) {
        if (absence == null) {
            throw new IllegalArgumentException("Absence cannot be null.");
        }
        // Check for existing absence on the same date and type to prevent duplicates
        for (Absence existingAbsence : absences) {
            if (existingAbsence.getDate().equals(absence.getDate()) &&
                existingAbsence.getType().equals(absence.getType())) {
                return false; // Absence already exists
            }
        }
        return absences.add(absence);
    }

    /**
     * Removes an absence record for the student.
     * The absence is identified by its date and type.
     *
     * @param absence The Absence object to remove.
     * @return true if the absence was removed successfully, false if it was not found.
     */
    public boolean removeAbsence(Absence absence) {
        if (absence == null) {
            throw new IllegalArgumentException("Absence cannot be null.");
        }
        // Remove the absence based on its equality (date and type)
        return absences.remove(absence);
    }

    /**
     * Retrieves an unmodifiable list of all absence records for this student.
     *
     * @return An unmodifiable list of Absence objects.
     */
    public List<Absence> getAbsences() {
        return Collections.unmodifiableList(absences);
    }

    /**
     * Overrides the equals method to compare Student objects based on their studentId.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this Student object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * Provides a string representation of the Student object.
     *
     * @return A string containing the student's ID, name, and parent email.
     */
    @Override
    public String toString() {
        return "Student{" +
               "studentId='" + studentId + '\'' +
               ", name='" + name + '\'' +
               ", parentEmail='" + parentEmail + '\'' +
               '}';
    }
}