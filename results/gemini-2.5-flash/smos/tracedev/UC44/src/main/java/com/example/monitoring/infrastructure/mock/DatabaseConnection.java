package com.example.monitoring.infrastructure.mock;

import com.example.monitoring.domain.SchoolYear;
import com.example.monitoring.domain.Student;
import com.example.monitoring.infrastructure.DatabaseConnectionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Infrastructure Layer (Mock): A mock class to simulate database interactions.
 * This class is used for testing and demonstration purposes to avoid actual database setup.
 * It can simulate successful queries, connection failures, and returning empty results.
 */
public class DatabaseConnection {
    // Flag to simulate database connection failures
    private boolean simulateConnectionFailure = false;
    // Flag to simulate returning an empty list of students
    private boolean returnEmptyList = false;

    /**
     * Simulates finding students in a database based on criteria.
     *
     * @param schoolYear The school year to filter by.
     * @param absenceThreshold The threshold for absences.
     * @param notesThreshold The threshold for notes.
     * @return A list of mock Student objects that meet the criteria.
     * @throws DatabaseConnectionException if {@code simulateConnectionFailure} is true.
     */
    public List<Student> findStudentsExceedingThresholds(SchoolYear schoolYear, int absenceThreshold, int notesThreshold)
            throws DatabaseConnectionException {
        // Simulate a delay for database query
        try {
            Thread.sleep(100); // Simulate network latency/query time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate database connection failure
        if (simulateConnectionFailure) {
            throw new DatabaseConnectionException("Simulated database connection lost.", new RuntimeException("Underlying network issue."));
        }

        // Simulate returning an empty list if requested
        if (returnEmptyList) {
            return new ArrayList<>();
        }

        // Generate some mock data that would typically come from a database
        List<Student> mockStudents = Arrays.asList(
                new Student("S001", "Alice Smith", 6, 4), // Exceeds both
                new Student("S002", "Bob Johnson", 3, 2), // Below both
                new Student("S003", "Charlie Brown", 8, 1), // Exceeds absences
                new Student("S004", "Diana Prince", 2, 5), // Exceeds notes
                new Student("S005", "Eve Adams", 5, 3) // At threshold, should not exceed if strictly greater than
        );

        // Filter mock students based on the provided thresholds and school year (simplified)
        // Assumption: For simplicity, the schoolYear parameter is acknowledged but not strictly
        // used in filtering the static mock data, as the mock data itself isn't tied to a specific year.
        List<Student> results = new ArrayList<>();
        for (Student student : mockStudents) {
            if (student.getAbsences() > absenceThreshold && student.getNotes() > notesThreshold) {
                results.add(student);
            }
        }
        System.out.println("Mock DB: Found " + results.size() + " students exceeding thresholds.");
        return results;
    }

    /**
     * Sets a flag to simulate a database connection failure.
     * @param simulateConnectionFailure If true, {@code findStudentsExceedingThresholds} will throw an exception.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    /**
     * Sets a flag to simulate returning an empty list from the database.
     * @param returnEmptyList If true, {@code findStudentsExceedingThresholds} will return an empty list.
     */
    public void setReturnEmptyList(boolean returnEmptyList) {
        this.returnEmptyList = returnEmptyList;
    }
}