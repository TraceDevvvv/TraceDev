package com.example.reportcardeditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Manages the collection of student report cards and provides functionality
 * for viewing and editing them. This class acts as the central repository
 * for report card data and business logic related to their modification.
 */
public class ReportCardManager {
    // Stores student data, using student ID as the key for quick lookup.
    // The map holds Student objects, which are immutable.
    private final Map<String, Student> students;

    /**
     * Constructs a new ReportCardManager with an empty collection of students.
     * Initial data can be added using the addStudent method.
     */
    public ReportCardManager() {
        this.students = new HashMap<>();
    }

    /**
     * Adds a new student to the manager's collection.
     * If a student with the same ID already exists, it will be replaced.
     *
     * @param student The {@link Student} object to add. Cannot be null.
     * @throws IllegalArgumentException if the student object is null.
     */
    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }
        students.put(student.getStudentId(), student);
    }

    /**
     * Retrieves a student by their unique ID.
     *
     * @param studentId The ID of the student to retrieve.
     * @return The {@link Student} object if found, otherwise null.
     */
    public Student getStudentById(String studentId) {
        return students.get(studentId);
    }

    /**
     * Retrieves an unmodifiable list of all students currently managed.
     * This simulates displaying a list of students.
     *
     * @return An unmodifiable {@link List} of {@link Student} objects.
     */
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students.values()));
    }

    /**
     * Simulates the "DisplayedUnapagella" use case by providing the current
     * report card for a specific student, ready for modification.
     *
     * @param studentId The ID of the student whose report card is to be displayed.
     * @return A {@link ReportCard} object for the specified student.
     * @throws IllegalArgumentException if no student with the given ID is found.
     */
    public ReportCard displayStudentReportCardForEdit(String studentId) {
        Student student = getStudentById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found.");
        }
        // Return a new ReportCard instance based on the current student data
        return new ReportCard(student);
    }

    /**
     * Edits the report card of a specific student. This method creates a new
     * {@link Student} object with the updated details and replaces the old
     * student record in the manager. Due to the immutability of {@link Student}
     * and {@link Subject}, an "edit" operation results in new objects being created.
     *
     * @param studentId The ID of the student whose report card is to be edited.
     * @param newStudentName The updated name of the student.
     * @param updatedSubjects A list of {@link Subject} objects representing the student's
     *                        new or updated subjects and grades. This list replaces all
     *                        previous subjects for the student.
     * @return The newly created and updated {@link ReportCard} for the student.
     * @throws IllegalArgumentException if no student with the given ID is found,
     *                                  or if newStudentName or updatedSubjects are invalid.
     */
    public ReportCard editStudentReportCard(String studentId, String newStudentName, List<Subject> updatedSubjects) {
        // Check if the student exists
        if (!students.containsKey(studentId)) {
            throw new IllegalArgumentException("Student with ID " + studentId + " not found for editing.");
        }

        // Create a new Student object with the updated data.
        // The studentId remains the same, but name and subjects are updated.
        Student updatedStudent = new Student(studentId, newStudentName, updatedSubjects);

        // Replace the old student object with the new, updated one in the map.
        students.put(studentId, updatedStudent);

        // Return the report card for the newly updated student.
        return new ReportCard(updatedStudent);
    }

    /**
     * Simulates the "user interrupts the connection to the SMOS server interrupted" postcondition.
     * In a real application, this would involve session management, database connection closing, etc.
     * For this simulation, it simply prints a message.
     */
    public void interruptConnection() {
        System.out.println("Administrator session interrupted. Connection to SMOS server terminated.");
        // In a real system, this might involve:
        // - Closing database connections
        // - Invalidating user sessions
        // - Releasing resources
    }
}