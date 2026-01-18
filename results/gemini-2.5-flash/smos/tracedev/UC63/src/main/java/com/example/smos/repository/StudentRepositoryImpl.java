package com.example.smos.repository;

import com.example.smos.model.Student;
import com.example.smos.exception.SMOSServerConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Concrete implementation of IStudentRepository for data access.
 * This class corresponds to the 'StudentRepositoryImpl' class in the UML diagram.
 * It provides mock data for demonstration purposes.
 */
public class StudentRepositoryImpl implements IStudentRepository {

    // Flag to simulate a server connection failure for testing REQ-008.
    // Set to true to force a connection exception.
    private boolean simulateConnectionFailure = false;

    /**
     * Sets whether the repository should simulate a server connection failure.
     * @param simulateConnectionFailure true to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    /**
     * Retrieves a list of all students.
     * This is a mock implementation that returns a hardcoded list of students.
     * It can also simulate a server connection exception.
     *
     * @return A list of Student objects.
     * @throws SMOSServerConnectionException If {@code simulateConnectionFailure} is true or a random condition is met.
     */
    @Override
    public List<Student> findStudents() throws SMOSServerConnectionException {
        // Simulate a server connection failure based on the flag
        if (simulateConnectionFailure) {
            throw new SMOSServerConnectionException("Simulated connection failure to SMOS server.");
        }

        // Simulate a server connection failure with a 30% chance for more dynamic testing
        // This is removed because the sequence diagram has a specific "Server connection failure" branch that
        // is better controlled by a flag for testing.
        // if (new Random().nextInt(10) < 3) { // 30% chance of failure
        //     throw new SMOSServerConnectionException("Random simulated connection failure to SMOS server.");
        // }


        // Mock data for demonstration
        List<Student> students = new ArrayList<>();
        students.add(new Student("S001", "Alice Smith", 5, 2));   // High absences, low notes
        students.add(new Student("S002", "Bob Johnson", 1, 8));  // Low absences, high notes
        students.add(new Student("S003", "Charlie Brown", 3, 5)); // Moderate
        students.add(new Student("S004", "Diana Prince", 8, 1)); // Very high absences, low notes
        students.add(new Student("S005", "Eve Adams", 0, 10));   // No absences, very high notes
        students.add(new Student("S006", "Frank White", 2, 3));  // Low absences, low notes
        return students;
    }
}